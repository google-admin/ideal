/*
 * Copyright 2014 The Ideal Authors. All rights reserved.
 *
 * Use of this source code is governed by a BSD-style
 * license that can be found in the LICENSE file or at
 * https://developers.google.com/open-source/licenses/bsd
 */

package ideal.experiment.mini;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class create {

  public interface source {
    @Nullable source deeper();
  }

  public static class source_text implements source {
    public final String name;
    public final String content;

    public source_text(String name, String content) {
      this.name = name;
      this.content = content;
    }

    public @Nullable source deeper() {
      return null;
    }
  }

  public static class text_position implements source {
    public final source_text the_source_text;
    public final int character_index;

    public text_position(source_text the_source_text, int character_index) {
      this.the_source_text = the_source_text;
      this.character_index = character_index;
    }

    public source deeper() {
      return the_source_text;
    }
  }

  public static enum notification_type {
    UNRECOGNIZED_CHARACTER("Unrecognized character"),
    EOF_IN_STRING_LITERAL("End of file in string literal"),
    NEWLINE_IN_STRING_LITERAL("Newline in string literal"),
    PARSE_ERROR("Parse error"),
    CLOSE_PAREN_NOT_FOUND("Close parenthesis not found");

    public final String message;

    notification_type(String message) {
      this.message = message;
    }
  }

  public static class notification {
    public final notification_type type;
    public final source the_source;

    public notification(notification_type type, source the_source) {
      this.type = type;
      this.the_source = the_source;
    }
  }

  public interface construct extends source {
    <result, parameter> result accept(
        construct_visitor<result, parameter> visitor, parameter the_parameter);
  }

  public interface construct_visitor<result, parameter> {
    result accept_identifier(identifier the_identifier, parameter the_parameter);
    result accept_string_literal(string_literal the_string_literal, parameter the_parameter);
    result accept_s_expression(s_expression the_s_expression, parameter the_parameter);
    result accept_variable_construct(variable_construct the_variable_construct,
        parameter the_parameter);
    result accept_type_declaration_construct(
        type_declaration_construct the_type_declaration_construct, parameter the_parameter);
  }

  public static enum token_type {
    WHITESPACE,
    OPEN,
    CLOSE,
    IDENTIFIER,
    LITERAL;
  }

  public static interface token extends source {
    token_type type();
  }

  public static class simple_token implements token {
    public final token_type type;
    public final source the_source;

    public simple_token(token_type type, source the_source) {
      this.type = type;
      this.the_source = the_source;
    }

    @Override
    public token_type type() {
      return type;
    }

    @Override
    public source deeper() {
      return the_source;
    }

    @Override
    public String toString() {
      return "<" + type.toString() + ">";
    }
  }

  public static class identifier implements construct, token {
    public final String name;
    public final source the_source;

    public identifier(String name, source the_source) {
      this.name = name;
      this.the_source = the_source;
    }

    @Override
    public token_type type() {
      return token_type.IDENTIFIER;
    }

    @Override
    public source deeper() {
      return the_source;
    }

    @Override
    public <result, parameter> result accept(
        construct_visitor<result, parameter> visitor, parameter the_parameter) {
      return visitor.accept_identifier(this, the_parameter);
    }

    @Override
    public String toString() {
      return "<identifier:" + name + ">";
    }
  }

  public static class string_literal implements construct, token {
    public final String value;
    public final String with_quotes;
    public final source the_source;

    public string_literal(String value, String with_quotes, source the_source) {
      this.value = value;
      this.with_quotes = with_quotes;
      this.the_source = the_source;
    }

    @Override
    public token_type type() {
      return token_type.LITERAL;
    }

    @Override
    public source deeper() {
      return the_source;
    }

    @Override
    public <result, parameter> result accept(
        construct_visitor<result, parameter> visitor, parameter the_parameter) {
      return visitor.accept_string_literal(this, the_parameter);
    }

    @Override
    public String toString() {
      return "<string_literal:" + value + ">";
    }
  }

  public static class s_expression implements construct {
    public final List<construct> parameters;
    public final source the_source;

    public s_expression(List<construct> parameters, source the_source) {
      this.parameters = parameters;
      this.the_source = the_source;
    }

    @Override
    public source deeper() {
      return the_source;
    }

    @Override
    public <result, parameter> result accept(
        construct_visitor<result, parameter> visitor, parameter the_parameter) {
      return visitor.accept_s_expression(this, the_parameter);
    }

    @Override
    public String toString() {
      return "s-expr:" + fn_display_list(parameters);
    }
  }

  // TODO(dynin): implement annotations.
  public interface annotation extends construct {
  }

  public static class variable_construct implements construct {
    public final List<annotation> annotations;
    public final @Nullable construct type;
    public final String name;
    public final @Nullable construct initializer;
    public final source the_source;

    public variable_construct(
        List<annotation> annotations,
        @Nullable construct type,
        String name,
        @Nullable construct initializer,
        source the_source) {
      this.annotations = annotations;
      this.type = type;
      this.name = name;
      this.initializer = initializer;
      this.the_source = the_source;
    }

    @Override
    public source deeper() {
      return the_source;
    }

    @Override
    public <result, parameter> result accept(
        construct_visitor<result, parameter> visitor, parameter the_parameter) {
      return visitor.accept_variable_construct(this, the_parameter);
    }

    @Override
    public String toString() {
      StringBuilder result = new StringBuilder("variable:<")
          .append(fn_display_list(annotations))
          .append(" type:").append(type)
          .append(" name:").append(name)
          .append(" init:").append(initializer)
          .append(" source:").append(the_source)
          .append(">");
      return result.toString();
    }
  }

  public static enum kind {
    DATATYPE,
    INTERFACE;
  }

  public static class type_declaration_construct implements construct {
    public final List<annotation> annotations;
    public final kind the_kind;
    public final String name;
    public final List<construct> body;
    public final source the_source;

    public type_declaration_construct(
        List<annotation> annotations,
        kind the_kind,
        String name,
        List<construct> body,
        source the_source) {
      this.annotations = annotations;
      this.the_kind = the_kind;
      this.name = name;
      this.body = body;
      this.the_source = the_source;
    }

    @Override
    public source deeper() {
      return the_source;
    }

    @Override
    public <result, parameter> result accept(
        construct_visitor<result, parameter> visitor, parameter the_parameter) {
      return visitor.accept_type_declaration_construct(this, the_parameter);
    }

    @Override
    public String toString() {
      StringBuilder result = new StringBuilder("type_declaration:<")
          .append(fn_display_list(annotations))
          .append(" kind:").append(the_kind)
          .append(" name:").append(name)
          .append(" body:").append(fn_display_list(body))
          .append(" source:").append(the_source)
          .append(">");
      return result.toString();
    }
  }

  public static List<token> tokenize(source_text the_source_text) {
    String content = the_source_text.content;
    int index = 0;
    List<token> result = new ArrayList<token>();
    while (index < content.length()) {
      int start = index;
      char prefix = content.charAt(index);
      index += 1;
      source position = new text_position(the_source_text, start);
      if (fn_is_letter(prefix)) {
        while (index < content.length() && fn_is_letter(content.charAt(index))) {
          index += 1;
        }
        result.add(new identifier(content.substring(start, index), position));
      } else if (fn_is_whitespace(prefix)) {
        while (index < content.length() && fn_is_whitespace(content.charAt(index))) {
          index += 1;
        }
        result.add(new simple_token(token_type.WHITESPACE, position));
      } else if (prefix == '(') {
        result.add(new simple_token(token_type.OPEN, position));
      } else if (prefix == ')') {
        result.add(new simple_token(token_type.CLOSE, position));
      } else if (prefix == '"') {
        char quote = prefix;
        while (index < content.length() &&
               content.charAt(index) != quote &&
               content.charAt(index) != '\n') {
          index += 1;
        }
        if (index == content.length()) {
          report(new notification(notification_type.EOF_IN_STRING_LITERAL, position));
        } else if (content.charAt(index) == '\n') {
          index += 1;
          report(new notification(notification_type.NEWLINE_IN_STRING_LITERAL, position));
        } else {
          assert content.charAt(index) == quote;
          String value = content.substring(start + 1, index);
          String with_quotes = content.substring(start, index + 1);
          index += 1;
          result.add(new string_literal(value, with_quotes, position));
        }
      } else {
        report(new notification(notification_type.UNRECOGNIZED_CHARACTER, position));
      }
    }
    return result;
  }

  public static boolean fn_is_letter(char c) {
    return Character.isLetter(c);
  }

  public static boolean fn_is_whitespace(char c) {
    return Character.isWhitespace(c);
  }

  public static String fn_display_list(List the_list) {
    StringBuilder result = new StringBuilder("[");
    for (int i = 0; i < the_list.size(); ++i) {
      if (i > 0) {
        result.append(' ');
      }
      result.append(the_list.get(i).toString());
    }
    result.append(']');

    return result.toString();
  }

  public static void report(notification the_notification) {
    String message = the_notification.type.message;

    @Nullable source deep_source = the_notification.the_source;
    while(deep_source != null) {
      if (deep_source instanceof text_position) {
        text_position position = (text_position) deep_source;
        String content = position.the_source_text.content;
        int index = position.character_index;
        int line_number = 1;
        for (int i = index - 1; i >= 0; --i) {
          if (content.charAt(i) == '\n') {
            line_number += 1;
          }
        }
        StringBuilder detailed = new StringBuilder();
        detailed.append(position.the_source_text.name).append(":");
        detailed.append(line_number).append(": ");
        detailed.append(message).append('\n');

        int start_of_line = index;
        while (start_of_line > 0 && content.charAt(start_of_line - 1) != '\n') {
          start_of_line -= 1;
        }
        int spaces;
        if (index >= content.length() || content.charAt(index) == '\n') {
          detailed.append(content.substring(start_of_line, index));
          detailed.append('\n');
        } else {
          int end_of_line = index;
          while (end_of_line < (content.length() - 1) && content.charAt(end_of_line + 1) != '\n') {
            end_of_line += 1;
          }
          detailed.append(content.substring(start_of_line, end_of_line + 1));
          detailed.append('\n');
        }
        for (int i = 0; i < (index - start_of_line); ++i) {
          detailed.append(' ');
        }
        detailed.append('^');
        // Last newline is added by println().
        message = detailed.toString();
        break;
      }
      if (deep_source instanceof source_text) {
        message = ((source_text) deep_source).name + ": " + message;
        break;
      }
      deep_source = deep_source.deeper();
    }

    System.err.println(message);
  }

  public static List<token> filter_whitespace(List<token> tokens) {
    List<token> result = new ArrayList<token>();

    for (token the_token : tokens) {
      if (the_token.type() != token_type.WHITESPACE) {
        result.add(the_token);
      }
    }

    return result;
  }

  private static int parse_sublist(List<token> tokens, int start, List<construct> result,
      parser_context context) {
    int index = start;
    while (index < tokens.size()) {
      token the_token = tokens.get(index);
      index += 1;
      if (the_token instanceof construct) {
        result.add((construct) the_token);
      } else if (the_token.type() == token_type.OPEN) {
        List<construct> parameters = new ArrayList<construct>();
        int end = parse_sublist(tokens, index, parameters, context);
        if (end >= tokens.size()) {
          report(new notification(notification_type.CLOSE_PAREN_NOT_FOUND, the_token));
        } else if (tokens.get(end).type() != token_type.CLOSE) {
          report(new notification(notification_type.CLOSE_PAREN_NOT_FOUND, tokens.get(end)));
        } else {
          end += 1;
        }
        index = end;
        if (parameters.size() > 0 &&
            parameters.get(0) instanceof identifier) {
          String name = ((identifier) parameters.get(0)).name;
          @Nullable special_parser the_parser = context.get_parser(name);
          if (the_parser != null) {
            @Nullable construct parsed = the_parser.parse(parameters.subList(1, parameters.size()));
            if (parsed != null) {
              result.add(parsed);
            }
            continue;
          }
        }
        result.add(new s_expression(parameters, the_token));
      } else if (the_token.type() == token_type.CLOSE) {
        return index - 1;
      } else {
        report(new notification(notification_type.PARSE_ERROR, the_token));
      }
    }

    return index;
  }

  public interface special_parser {
    @Nullable construct parse(List<construct> parameters);
  }

  public interface parser_context {
    @Nullable special_parser get_parser(String name);
  }

  public static final special_parser VARIABLE_PARSER = new special_parser() {
    @Override
    public @Nullable construct parse(List<construct> parameters) {
      if (parameters.size() != 3) {
        return null;
      }
      if (!(parameters.get(2) instanceof identifier)) {
        return null;
      }

      List<annotation> annotations = parse_annotations(parameters.get(0));
      construct type = parameters.get(1);
      String name = ((identifier) parameters.get(2)).name;
      @Nullable construct initializer = null;
      source the_source = parameters.get(2);

      return new variable_construct(annotations, type, name, initializer, the_source);
    }
  };

  public static final special_parser DATATYPE_PARSER = new special_parser() {
    @Override
    public @Nullable construct parse(List<construct> parameters) {
      if (parameters.size() != 3) {
        return null;
      }
      if (!(parameters.get(1) instanceof identifier)) {
        return null;
      }
      if (!(parameters.get(2) instanceof s_expression)) {
        return null;
      }

      List<annotation> annotations = parse_annotations(parameters.get(0));
      kind the_kind = kind.DATATYPE;
      String name = ((identifier) parameters.get(1)).name;
      List<construct> body = ((s_expression) parameters.get(2)).parameters;
      source the_source = parameters.get(1);

      return new type_declaration_construct(annotations, the_kind, name, body, the_source);
    }
  };


  private static List<annotation> parse_annotations(construct the_construct) {
    // TODO(dynin): implement actual annotation parsing.
    if (!(the_construct instanceof s_expression) ||
        !((s_expression) the_construct).parameters.isEmpty()) {
      report(new notification(notification_type.PARSE_ERROR, the_construct));
    }
    return new ArrayList<annotation>();
  }

  public static class common_context implements parser_context {
    @Override
    public @Nullable special_parser get_parser(String name) {
      if (name.equals("variable")) {
        return VARIABLE_PARSER;
      } else if (name.equals("datatype")) {
        return DATATYPE_PARSER;
      } else {
        return null;
      }
    }
  }

  public static List<construct> parse(List<token> tokens, parser_context context) {
    List<construct> result = new ArrayList<construct>();
    int consumed = parse_sublist(tokens, 0, result, context);
    if (consumed < tokens.size()) {
      report(new notification(notification_type.PARSE_ERROR, tokens.get(consumed)));
    }
    return result;
  }

  public static final text OPEN_PAREN = new text_string("(");
  public static final text CLOSE_PAREN = new text_string(")");
  public static final text OPEN_BRACE = new text_string("{");
  public static final text CLOSE_BRACE = new text_string("}");
  public static final text NEWLINE = new text_string("\n");
  public static final text SEMICOLON = new text_string(";");

  public static class base_printer implements function<text, construct>,
      construct_visitor<text, Void> {
    @Override
    public text call(construct the_construct) {
      return the_construct.accept(this, null);
    }

    public text print(List<construct> constructs) {
      return join_text(map(constructs, this));
    }

    @Override
    public text accept_identifier(identifier the_identifier, Void nothing) {
      return new text_string(the_identifier.name);
    }

    @Override
    public text accept_string_literal(string_literal the_string_literal, Void nothing) {
      return new text_string(the_string_literal.with_quotes);
    }

    @Override
    public text accept_s_expression(s_expression the_s_expression, Void nothing) {
      return join_text(OPEN_PAREN, join_text(map(the_s_expression.parameters, this), SPACE),
          CLOSE_PAREN);

    }

    @Override
    public text accept_variable_construct(variable_construct the_variable_construct,
        Void nothing) {
      List<text> result = new ArrayList<text>();
      result.add(call(the_variable_construct.type));
      result.add(SPACE);
      result.add(new text_string(the_variable_construct.name));
      result.add(SEMICOLON);
      result.add(NEWLINE);
      return new text_list(result);
    }

    @Override
    public text accept_type_declaration_construct(
        type_declaration_construct the_type_declaration_construct, Void nothing) {
      List<text> result = new ArrayList<text>();
      result.add(new text_string(the_type_declaration_construct.the_kind.toString().toLowerCase()));
      result.add(SPACE);
      result.add(new text_string(the_type_declaration_construct.name));
      result.add(SPACE);
      result.add(OPEN_BRACE);
      result.add(NEWLINE);
      result.add(new indented_text(print(the_type_declaration_construct.body)));
      result.add(CLOSE_BRACE);
      result.add(NEWLINE);
      return new text_list(result);
    }
  }

  public interface function<result, parameter> {
    result call(parameter the_parameter);
  }

  public static <source_type, target_type> List<target_type> map(List<source_type> source_list,
      function<target_type, source_type> transform) {
    List<target_type> result = new ArrayList<target_type>();
    for (source_type source_element : source_list) {
      result.add(transform.call(source_element));
    }
    return result;
  }

  public interface text {
  }

  public static class text_string implements text {
    public final String value;

    public text_string(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return value;
    }
  }

  public static final text SPACE = new text_string(" ");

  public static class indented_text implements text {
    public final text inside;

    public indented_text(text inside) {
      this.inside = inside;
    }

    @Override
    public String toString() {
      return ">>" + inside.toString();
    }
  }

  public static class text_list implements text {
    public final List<text> texts;

    public text_list(List<text> texts) {
      this.texts = texts;
    }

    @Override
    public String toString() {
      StringBuilder result = new StringBuilder();
      for (text the_text : texts) {
        result.append(the_text.toString());
      }
      return result.toString();
    }
  }

  public static text join_text(text first, text second) {
    List<text> result = new ArrayList<text>();
    result.add(first);
    result.add(second);
    return new text_list(result);
  }

  public static text join_text(text first, text second, text third) {
    List<text> result = new ArrayList<text>();
    result.add(first);
    result.add(second);
    result.add(third);
    return new text_list(result);
  }

  public static text join_text(List<text> texts) {
    return new text_list(texts);
  }

  private static final String INDENT_STRING = "  ";

  private static boolean do_render_text(text the_text, boolean first, int indent,
      StringBuilder result) {
    if (the_text instanceof text_string) {
      String value = ((text_string) the_text).value;
      for (int i = 0; i < value.length(); ++i) {
        char c = value.charAt(i);
        if (c != '\n') {
          if (first) {
            for (int j = 0; j < indent; ++j) {
              result.append(INDENT_STRING);
            }
          }
          result.append(c);
          first = false;
        } else {
          result.append('\n');
          first = true;
        }
      }
    } else if (the_text instanceof indented_text) {
      first = do_render_text(((indented_text) the_text).inside, first, indent + 1, result);
    } else {
      assert the_text instanceof text_list;
      for (text sub_text : ((text_list)the_text).texts) {
        first = do_render_text(sub_text, first, indent, result);
      }
    }

    return first;
  }

  public static String render_text(text the_text) {
    StringBuilder result = new StringBuilder();
    do_render_text(the_text, true, 0, result);
    return result.toString();
  }

  public static text join_text(List<text> texts, text separator) {
    List<text> result = new ArrayList<text>();
    for (int i = 0; i < texts.size(); ++i) {
      if (i > 0) {
        result.add(separator);
      }
      result.add(texts.get(i));
    }
    return new text_list(result);
  }

  private static final boolean DEBUG_TOKENIZER = false;

  private static final boolean DEBUG_PARSER = true;

  public static void main(String[] args) {
    String file_name = args[0];
    String file_content = "";

    try {
      file_content = read_file(file_name);
    } catch (IOException e) {
      System.err.println("Can't read " + file_name);
      System.exit(1);
    }

    source_text the_source = new source_text(file_name, file_content);
    List<token> tokens = filter_whitespace(tokenize(the_source));
    if (DEBUG_TOKENIZER) {
      for (token the_token : tokens) {
        System.out.println(the_token.toString());
      }
    }

    List<construct> constructs = parse(tokens, new common_context());
    if (DEBUG_PARSER) {
      for (construct the_construct : constructs) {
        System.out.println(the_construct.toString());
      }
    }

    System.out.print(render_text(new base_printer().print(constructs)));
  }

  private static String read_file(String file_name) throws IOException {
    byte[] encoded = Files.readAllBytes(Paths.get(file_name));
    return new String(encoded, StandardCharsets.UTF_8);
  }
}
