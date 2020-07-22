// Autogenerated from runtime/elements/utilities.i

package ideal.runtime.elements;

import ideal.library.elements.*;
import java.lang.String;
import ideal.machine.elements.runtime_util;

import javax.annotation.Nullable;

public class utilities {
  private utilities() { }
  public static String s(final string the_string) {
    return base_string.unbox(the_string);
  }
  public static boolean eq(final @Nullable readonly_equality_comparable first, final @Nullable readonly_equality_comparable second) {
    final boolean first_null = first == null;
    final boolean second_null = second == null;
    if (first_null && second_null) {
      {
        utilities.panic(new base_string("double nulls in comparison"));
        return false;
      }
    }
    if (first_null || second_null) {
      return false;
    }
    return runtime_util.default_equivalence.call(first, second);
  }
  public static final string open_bracket = new base_string("[");
  public static final string close_bracket = new base_string("]");
  public static final string colon = new base_string(": ");
  public static string describe(final readonly_value the_value) {
    return new base_string(utilities.open_bracket, runtime_util.value_identifier(the_value), utilities.close_bracket);
  }
  public static string describe(final readonly_value the_value, final readonly_stringable details) {
    if (details == null) {
      return utilities.describe(the_value);
    } else {
      return new base_string(utilities.open_bracket, runtime_util.value_identifier(the_value), utilities.colon, details.to_string(), utilities.close_bracket);
    }
  }
  public static void panic(final string message) {
    runtime_util.do_panic(utilities.s(message));
  }
  public static void panic(final String message) {
    runtime_util.do_panic(message);
  }
}
