// Autogenerated from runtime/characters/quoted_character.i

package ideal.runtime.characters;

import ideal.library.elements.*;
import ideal.library.characters.*;
import ideal.runtime.elements.*;

public class quoted_character implements deeply_immutable_data, stringable {
  public static final char ESCAPE = '\\';
  public final string name;
  public final char name_character;
  public final char value_character;
  public final Integer ascii_code;
  public quoted_character(final string name, final char name_character, final char value_character, final Integer ascii_code) {
    this.name = name;
    this.name_character = name_character;
    this.value_character = value_character;
    this.ascii_code = ascii_code;
  }
  public string with_escape() {
    return ideal.machine.elements.runtime_util.concatenate(quoted_character.ESCAPE, this.value_character);
  }
  public @Override string to_string() {
    return ideal.machine.elements.runtime_util.concatenate(ideal.machine.elements.runtime_util.concatenate('<', this.name), '>');
  }
  public static final readonly_list<quoted_character> json_list = new base_immutable_list<quoted_character>(new ideal.machine.elements.array<quoted_character>(new quoted_character[]{ new quoted_character(new base_string("backspace"), 'b', '\b', 8), new quoted_character(new base_string("formfeed"), 'f', '\f', 12), new quoted_character(new base_string("newline"), 'n', '\n', 10), new quoted_character(new base_string("carriage return"), 'r', '\r', 13), new quoted_character(new base_string("horizontal tab"), 't', '\t', 9), new quoted_character(new base_string("double quote"), '\"', '\"', 34), new quoted_character(new base_string("slash"), '/', '/', 47), new quoted_character(new base_string("backslash"), quoted_character.ESCAPE, quoted_character.ESCAPE, 92) }));
  public static readonly_list<quoted_character> all_list;
  static {
    final base_list<quoted_character> the_list = new base_list<quoted_character>();
    the_list.append_all(quoted_character.json_list);
    the_list.append(new quoted_character(new base_string("single quote"), '\'', '\'', 39));
    quoted_character.all_list = the_list;
  }
}
