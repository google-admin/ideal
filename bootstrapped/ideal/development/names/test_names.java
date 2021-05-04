// Autogenerated from development/names/test_names.i

package ideal.development.names;

import ideal.library.elements.*;
import ideal.runtime.elements.*;
import ideal.runtime.logs.*;
import ideal.development.elements.*;
import ideal.development.symbols.base_symbols;

public class test_names {
  public void run_all_tests() {
    ideal.machine.elements.runtime_util.start_test("test_names.test_simple_names");
    test_simple_names();
    ideal.machine.elements.runtime_util.end_test();
    ideal.machine.elements.runtime_util.start_test("test_names.test_special_names");
    test_special_names();
    ideal.machine.elements.runtime_util.end_test();
    ideal.machine.elements.runtime_util.start_test("test_names.test_segmented_names");
    test_segmented_names();
    ideal.machine.elements.runtime_util.end_test();
    ideal.machine.elements.runtime_util.start_test("test_names.test_simple_names_equality");
    test_simple_names_equality();
    ideal.machine.elements.runtime_util.end_test();
    ideal.machine.elements.runtime_util.start_test("test_names.test_camel_case");
    test_camel_case();
    ideal.machine.elements.runtime_util.end_test();
  }
  public void test_simple_names() {
    final simple_name foo = simple_name.make(new base_string("foo"));
    final simple_name bar = simple_name.make(new base_string("bar"));
    final simple_name foo2 = simple_name.make(new base_string("foo"));
    assert ideal.machine.elements.runtime_util.values_equal(foo.to_string(), new base_string("foo"));
    assert ideal.machine.elements.runtime_util.values_equal(bar.to_string(), new base_string("bar"));
    assert foo != bar;
    assert foo == foo2;
  }
  public void test_special_names() {
    final special_name foo = new special_name(new base_string("foo"));
    final special_name bar = new special_name(new base_string("bar"));
    final special_name foo2 = new special_name(new base_string("foo"));
    assert ideal.machine.elements.runtime_util.values_equal(foo.to_string(), new base_string("<foo>"));
    assert ideal.machine.elements.runtime_util.values_equal(bar.to_string(), new base_string("<bar>"));
    assert foo != bar;
    assert foo != foo2;
  }
  public void test_segmented_names() {
    final simple_name foo = simple_name.make(new base_string("foo"));
    final simple_name bar = simple_name.make(new base_string("bar"));
    final simple_name name = name_utilities.join(foo, bar);
    assert ideal.machine.elements.runtime_util.values_equal(name.to_string(), new base_string("foo_bar"));
    final simple_name name2 = name_utilities.join(name, foo);
    assert ideal.machine.elements.runtime_util.values_equal(name2.to_string(), new base_string("foo_bar_foo"));
  }
  public void test_simple_names_equality() {
    final simple_name foo = simple_name.make(new base_string("foo_bar"));
    final simple_name bar = simple_name.make(new base_string("foo_bar"));
    assert foo == bar;
  }
  public void test_camel_case() {
    final simple_name the_name = name_utilities.parse_camel_case(new base_string("thisIsCamelCase"));
    assert ideal.machine.elements.runtime_util.values_equal(the_name.to_string(), new base_string("this_is_camel_case"));
    final simple_name the_name2 = name_utilities.parse_camel_case(new base_string("ThatIsCamelCase"));
    assert ideal.machine.elements.runtime_util.values_equal(the_name2.to_string(), new base_string("that_is_camel_case"));
  }
  public test_names() { }
}
