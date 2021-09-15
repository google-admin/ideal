// Autogenerated from development/types/type_action.i

package ideal.development.types;

import ideal.library.elements.*;
import ideal.library.reflections.*;
import ideal.runtime.elements.*;
import ideal.runtime.logs.*;
import ideal.development.elements.*;
import ideal.development.names.*;
import ideal.development.flavors.*;
import ideal.development.declarations.*;
import ideal.development.kinds.*;

import javax.annotation.Nullable;

public abstract class type_action extends debuggable implements action {
  private final origin the_origin;
  protected type_action(final origin the_origin) {
    assert the_origin != null;
    this.the_origin = the_origin;
  }
  public abstract type get_type();
  public @Override abstract_value result() {
    return this.get_type();
  }
  public @Override final origin deeper_origin() {
    return this.the_origin;
  }
  public @Override action bind_from(final action from, final origin new_origin) {
    if (new_origin == this.the_origin) {
      return this;
    } else {
      return new concrete_type_action(this.get_type(), new_origin);
    }
  }
  public @Override @Nullable declaration get_declaration() {
    return this.get_type().principal().get_declaration();
  }
  public @Override boolean has_side_effects() {
    return false;
  }
  public @Override entity_wrapper execute(final entity_wrapper from_entity, final execution_context context) {
    return new typeinfo_value(this.get_type());
  }
  public @Override string to_string() {
    return utilities.describe(this, this.get_type());
  }
}
