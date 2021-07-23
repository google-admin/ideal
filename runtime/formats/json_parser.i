-- Copyright 2014-2021 The Ideal Authors. All rights reserved.
--
-- Use of this source code is governed by a BSD-style
-- license that can be found in the LICENSE file or at
-- https://developers.google.com/open-source/licenses/bsd

--- JSON parser implementation.
class json_parser {
--  implicit import ideal.runtime.formats.json_token;
  import ideal.runtime.patterns.list_pattern;
  import ideal.machine.channels.string_writer;

  character_handler the_character_handler;
  -- TODO: use data instead of equality_comparable
  var list[readonly data] tokens;
  var string or null error;

  json_parser(character_handler the_character_handler) {
    this.the_character_handler = the_character_handler;
    tokens = base_list[readonly data].new();
  }

  boolean has_error => error is_not null;

  private void tokenize(string input) {
    tokens.clear();
    var nonnegative start : 0;
    while (start < input.size && error is null) {
      start = scan(input, start);
    }
  }

  list[readonly data] test_tokenize(string input) {
    tokenize(input);
    assert !has_error();
    return tokens;
  }

  private nonnegative scan(string input, nonnegative start) {
    next : input[start];
    var nonnegative index : start + 1;

    if (the_character_handler.is_whitespace(next)) {
      while (index < input.size && the_character_handler.is_whitespace(input[index])) {
        index += 1;
      }
      return index;
    }

    if (next == '"') {
      result : string_writer.new();
      while (index < input.size) {
        next_in_input : input[index];
        if (next_in_input == '"') {
          tokens.append(result.elements());
          return index + 1;
        } else if (next_in_input == '\\') {
          if (index >= input.size) {
            report_error("Escape at the end of input");
            return index;
          }
          index += 1;
          escaped_character : input[index];
          if (escaped_character == '"' ||
              escaped_character == '\\' ||
              escaped_character == '/') {
            result.write(escaped_character);
          } else if (escaped_character == 'b') {
            result.write('\b');
          } else if (escaped_character == 'f') {
            result.write('\f');
          } else if (escaped_character == 'n') {
            result.write('\n');
          } else if (escaped_character == 'r') {
            result.write('\r');
          } else if (escaped_character == 't') {
            result.write('\t');
          } else if (escaped_character == 'u') {
            if (index + 4 >= input.size) {
              report_error("Unicode escape at the end of input");
              return index;
            }
            var code : hex_digit(input[index + 1]);
            code = code * 16 + hex_digit(input[index + 2]);
            code = code * 16 + hex_digit(input[index + 3]);
            code = code * 16 + hex_digit(input[index + 4]);
            result.write(the_character_handler.from_code(code));
            index += 4;
          } else {
            report_error("Unrecognized escape character: " ++ escaped_character);
            return index;
          }
        } else {
          result.write(next_in_input);
        }
        index += 1;
      }
      report_error("No closing quote in a string");
      return index;
    }

    if (the_character_handler.is_digit(next)) {
      return parse_number(input, start, false);
    }

    if (next == '-') {
      if (index < input.size) {
        return parse_number(input, index, true);
      } else {
        report_error("Minus at the end of input");
        return index;
      }
    }

    -- TODO: iterate over json_tokens
    if (next == json_token.OPEN_BRACE.token) {
      tokens.append(json_token.OPEN_BRACE);
      return index;
    }

    if (next == json_token.CLOSE_BRACE.token) {
      tokens.append(json_token.CLOSE_BRACE);
      return index;
    }

    if (next == json_token.OPEN_BRACKET.token) {
      tokens.append(json_token.OPEN_BRACKET);
      return index;
    }

    if (next == json_token.CLOSE_BRACKET.token) {
      tokens.append(json_token.CLOSE_BRACKET);
      return index;
    }

    if (next == json_token.COMMA.token) {
      tokens.append(json_token.COMMA);
      return index;
    }

    if (next == json_token.COLON.token) {
      tokens.append(json_token.COLON);
      return index;
    }

    if (next == 't') {
      return parse_symbol(input, start, "true", true);
    }

    if (next == 'f') {
      return parse_symbol(input, start, "false", false);
    }

    if (next == 'n') {
      -- TODO: use native_null or another flavor of null other than missing
      return parse_symbol(input, start, "null", missing.instance);
    }

    report_error("Unrecognized character in a string: " ++ next);
    return index;
  }

  private nonnegative hex_digit(character the_character) {
    result : the_character_handler.from_digit(the_character, 16);
    if (result is nonnegative) {
      return result;
    } else {
      report_error("Unrecognized character in hex escape: " ++ the_character);
      return 0;
    }
  }

  private nonnegative parse_number(string input, nonnegative start, boolean negate) {
    next : input[start];
    if (!the_character_handler.is_digit(next)) {
      report_error("Unrecognized digit: " ++ next);
      return start;
    }

    digit : the_character_handler.from_digit(next, radix.DEFAULT_RADIX);
    assert digit is nonnegative;
    var nonnegative result : digit;
    var index : start + 1;
    while (index < input.size && the_character_handler.is_digit(input[index])) {
      next_digit : the_character_handler.from_digit(input[index], radix.DEFAULT_RADIX);
      assert next_digit is nonnegative;
      result = result * radix.DEFAULT_RADIX + next_digit;
      index += 1;
    }

    -- TODO: handle fraction and exponent
    tokens.append(negate ? -result : result);
    return index;
  }

  private nonnegative parse_symbol(string input, nonnegative start, string symbol,
      deeply_immutable data value) {
    prefix : list_pattern[character].new(symbol).match_prefix(input.skip(start));
    -- TODO: use && to join checks
    if (prefix is_not null) {
      if (prefix == symbol.size) {
        tokens.append(value);
        return start + prefix;
      }
    }

    report_error("Can't parse symbol: " ++ symbol);
    return start + 1;
  }

  private void report_error(string message) {
    error = message;
  }
}
