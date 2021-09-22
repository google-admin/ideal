// Autogenerated from development/jumps/jump_category.i

package ideal.development.jumps;

import ideal.library.elements.*;
import ideal.library.reflections.*;
import ideal.runtime.elements.*;
import ideal.development.elements.*;
import ideal.development.names.*;
import ideal.development.types.core_types;

public enum jump_category implements deeply_immutable_data, stringable {
  BREAK_JUMP(new base_string("break")),
  CONTINUE_JUMP(new base_string("continue"));
  private final string name_string;
  private jump_category(final string name_string) {
    this.name_string = name_string;
  }
  public simple_name jump_name() {
    return simple_name.make(this.name_string);
  }
  public @Override string to_string() {
    return this.name_string;
  }
}
