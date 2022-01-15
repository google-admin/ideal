// Autogenerated from development/values/enum_value.i

package ideal.development.values;

import ideal.library.elements.*;
import ideal.library.reflections.*;
import ideal.runtime.elements.*;
import ideal.runtime.reflections.*;
import ideal.development.elements.*;
import ideal.development.origins.*;
import ideal.development.names.*;
import ideal.development.flavors.*;
import ideal.development.declarations.*;
import ideal.development.kinds.*;
import ideal.development.types.*;
import ideal.development.jumps.*;
import ideal.development.notifications.*;
import ideal.development.flags.*;

public class enum_value extends base_data_value implements identifier {
  private final variable_declaration the_declaration;
  private final Integer ordinal;
  public enum_value(final variable_declaration the_declaration, final Integer ordinal, final type bound) {
    super(bound);
    this.the_declaration = the_declaration;
    this.ordinal = ordinal;
    assert the_declaration.short_name() instanceof simple_name;
  }
  public simple_name short_name() {
    return (simple_name) this.the_declaration.short_name();
  }
  public declaration get_declaration() {
    return this.the_declaration;
  }
  public @Override string to_string() {
    return this.short_name().to_string();
  }
}
