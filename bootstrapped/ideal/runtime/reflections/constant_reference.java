// Autogenerated from runtime/reflections/constant_reference.i

package ideal.runtime.reflections;

import ideal.library.elements.*;
import ideal.library.reflections.*;
import ideal.runtime.elements.*;

public class constant_reference<value_type> extends debuggable implements reference_wrapper<value_type> {
  private final value_wrapper<value_type> the_value;
  private final type_id the_reference_type;
  public constant_reference(final value_wrapper<value_type> the_value, final type_id the_reference_type) {
    this.the_value = the_value;
    this.the_reference_type = the_reference_type;
  }
  public @Override type_id type_bound() {
    return this.the_reference_type;
  }
  public @Override type_id value_type_bound() {
    return this.the_value.type_bound();
  }
  public @Override value_wrapper<value_type> get() {
    return this.the_value;
  }
  public @Override void init(final value_wrapper<value_type> new_value) {
    utilities.panic(new base_string("Can\'t init a constant_reference"));
  }
  public @Override void set(final value_wrapper<value_type> new_value) {
    utilities.panic(new base_string("Can\'t set a constant_reference"));
  }
  public @Override string to_string() {
    return utilities.describe(this, this.the_value);
  }
}
