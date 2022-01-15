// Autogenerated from development/values/common_values.i

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

import javax.annotation.Nullable;

public class common_values {
  private static action_context context;
  private static singleton_value VOID_INSTANCE;
  private static singleton_value MISSING_INSTANCE;
  private static singleton_value UNDEFINED_INSTANCE;
  private static @Nullable enum_value FALSE_VALUE;
  private static @Nullable enum_value TRUE_VALUE;
  public common_values(final action_context context) {
    common_values.context = context;
    common_values.VOID_INSTANCE = new singleton_value(common_types.void_type());
    common_values.MISSING_INSTANCE = new singleton_value(common_types.missing_type());
    common_values.UNDEFINED_INSTANCE = new singleton_value(common_types.undefined_type());
  }
  public static boolean is_initialized() {
    return common_values.context != null;
  }
  public static enum_value true_value() {
    if (common_values.TRUE_VALUE == null) {
      common_values.TRUE_VALUE = common_values.get_boolean_value(simple_name.make(new base_string("true")));
    }
    return common_values.TRUE_VALUE;
  }
  public static enum_value false_value() {
    if (common_values.FALSE_VALUE == null) {
      common_values.FALSE_VALUE = common_values.get_boolean_value(simple_name.make(new base_string("false")));
    }
    return common_values.FALSE_VALUE;
  }
  public static enum_value to_boolean_value(final boolean the_value) {
    return the_value ? common_values.true_value() : common_values.false_value();
  }
  public static singleton_value void_instance() {
    return common_values.VOID_INSTANCE;
  }
  public static singleton_value missing_instance() {
    return common_values.MISSING_INSTANCE;
  }
  public static singleton_value undefined_instance() {
    return common_values.UNDEFINED_INSTANCE;
  }
  public static action nothing(final origin the_origin) {
    return common_values.VOID_INSTANCE.to_action(the_origin);
  }
  public static boolean is_nothing(final action the_action) {
    return the_action instanceof base_value_action && ((base_value_action) the_action).result().type_bound().principal() == common_types.void_type();
  }
  private static enum_value get_boolean_value(final simple_name the_name) {
    final readonly_list<action> actions = common_values.context.lookup(common_types.boolean_type(), the_name);
    assert ideal.machine.elements.runtime_util.values_equal(actions.size(), 1);
    final abstract_value the_value = actions.first().result();
    assert the_value instanceof enum_value;
    return ((enum_value) the_value);
  }
}
