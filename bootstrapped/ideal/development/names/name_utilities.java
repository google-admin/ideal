// Autogenerated from development/names/name_utilities.i

package ideal.development.names;

import ideal.library.elements.*;
import ideal.runtime.elements.*;
import ideal.runtime.logs.*;
import ideal.development.elements.*;
import ideal.development.symbols.base_symbols;
import ideal.library.characters.*;
import ideal.machine.characters.*;
import ideal.machine.channels.string_writer;

public class name_utilities {
  private final static simple_name FIRST = simple_name.make(new base_string("first"));
  private final static simple_name SECOND = simple_name.make(new base_string("second"));
  private final static simple_name THIRD = simple_name.make(new base_string("third"));
  private final static normal_handler the_character_handler = normal_handler.instance;
  public static string in_brackets(final readonly_stringable name) {
    return ideal.machine.elements.runtime_util.concatenate(ideal.machine.elements.runtime_util.concatenate(new base_string("<"), name.to_string()), new base_string(">"));
  }
  public static simple_name make_numbered_name(final int index) {
    if (index == 0) {
      return FIRST;
    } else if (index == 1) {
      return SECOND;
    } else if (index == 2) {
      return THIRD;
    } else {
      utilities.panic(ideal.machine.elements.runtime_util.concatenate(new base_string("Don't know how to count up to "), index));
      return null;
    }
  }
  public static simple_name join(final simple_name first, final simple_name second) {
    final base_list<string> segments = new base_list<string>();
    segments.append_all(first.segments);
    segments.append_all(second.segments);
    return simple_name.make_from_segments(segments.frozen_copy());
  }
  public static simple_name parse_camel_case(final string name) {
    final base_list<string> segments = new base_list<string>();
    int index = 0;
    while (index < name.size()) {
      final string_writer the_writer = new string_writer();
      while (index < name.size() && the_character_handler.is_upper_case(name.get(index))) {
        the_writer.write(the_character_handler.to_lower_case(name.get(index)));
        index += 1;
      }
      while (index < name.size() && !the_character_handler.is_upper_case(name.get(index))) {
        the_writer.write(name.get(index));
        index += 1;
      }
      segments.append(the_writer.extract_elements());
    }
    if (!segments.is_empty()) {
      return simple_name.make_from_segments(segments.frozen_copy());
    } else {
      utilities.panic(ideal.machine.elements.runtime_util.concatenate(new base_string("Can't parse name "), name));
      return null;
    }
  }
}
