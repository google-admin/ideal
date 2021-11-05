// Autogenerated from runtime/channels/test_output_transformer.i

package ideal.runtime.channels;

import ideal.library.elements.*;
import ideal.library.channels.*;
import ideal.runtime.elements.*;

public class test_output_transformer {
  public static string test_transform(final string source) {
    return new base_string(new base_string("+"), source, new base_string("!"));
  }
  public void test_appender() {
    final appender<string> the_appender = new appender<string>();
    the_appender.write(new base_string("foo"));
    the_appender.write(new base_string("bar"));
    the_appender.write(new base_string("baz"));
    final immutable_list<string> elements = the_appender.elements();
    assert ideal.machine.elements.runtime_util.values_equal(elements.size(), 3);
    assert ideal.machine.elements.runtime_util.values_equal(elements.get(0), new base_string("foo"));
    assert ideal.machine.elements.runtime_util.values_equal(elements.get(1), new base_string("bar"));
    assert ideal.machine.elements.runtime_util.values_equal(elements.get(2), new base_string("baz"));
    the_appender.write_all(elements);
    final immutable_list<string> more_elements = the_appender.elements();
    assert ideal.machine.elements.runtime_util.values_equal(more_elements.size(), 6);
    assert ideal.machine.elements.runtime_util.values_equal(more_elements.get(0), new base_string("foo"));
    assert ideal.machine.elements.runtime_util.values_equal(more_elements.get(1), new base_string("bar"));
    assert ideal.machine.elements.runtime_util.values_equal(more_elements.get(2), new base_string("baz"));
    assert ideal.machine.elements.runtime_util.values_equal(more_elements.get(3), new base_string("foo"));
    assert ideal.machine.elements.runtime_util.values_equal(more_elements.get(4), new base_string("bar"));
    assert ideal.machine.elements.runtime_util.values_equal(more_elements.get(5), new base_string("baz"));
  }
  public void test_transformer() {
    final appender<string> the_appender = new appender<string>();
    final output_transformer<string, string> the_transformer = new output_transformer<string, string>(new function1<string, string>() {
      public @Override string call(string first) {
        return test_output_transformer.test_transform(first);
      }
    }, the_appender);
    the_transformer.write(new base_string("foo"));
    the_transformer.write(new base_string("bar"));
    the_transformer.write(new base_string("baz"));
    final immutable_list<string> elements = the_appender.elements();
    assert ideal.machine.elements.runtime_util.values_equal(elements.size(), 3);
    assert ideal.machine.elements.runtime_util.values_equal(elements.get(0), new base_string("+foo!"));
    assert ideal.machine.elements.runtime_util.values_equal(elements.get(1), new base_string("+bar!"));
    assert ideal.machine.elements.runtime_util.values_equal(elements.get(2), new base_string("+baz!"));
  }
  public test_output_transformer() { }
  public void run_all_tests() {
    ideal.machine.elements.runtime_util.start_test(new base_string("test_output_transformer.test_appender"));
    this.test_appender();
    ideal.machine.elements.runtime_util.end_test();
    ideal.machine.elements.runtime_util.start_test(new base_string("test_output_transformer.test_transformer"));
    this.test_transformer();
    ideal.machine.elements.runtime_util.end_test();
  }
}
