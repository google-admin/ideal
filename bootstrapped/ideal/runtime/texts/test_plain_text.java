// Autogenerated from runtime/texts/test_plain_text.i

package ideal.runtime.texts;

import ideal.library.elements.*;
import ideal.library.texts.*;
import ideal.runtime.elements.*;
import ideal.library.channels.output;
import ideal.machine.channels.string_writer;

public class test_plain_text {
  public void run_all_tests() {
    ideal.machine.elements.runtime_util.start_test("test_plain_text.test_writer_trivial");
    test_writer_trivial();
    ideal.machine.elements.runtime_util.end_test();
    ideal.machine.elements.runtime_util.start_test("test_plain_text.test_writer_indent0");
    test_writer_indent0();
    ideal.machine.elements.runtime_util.end_test();
    ideal.machine.elements.runtime_util.start_test("test_plain_text.test_writer_indent1");
    test_writer_indent1();
    ideal.machine.elements.runtime_util.end_test();
    ideal.machine.elements.runtime_util.start_test("test_plain_text.test_writer_indent2");
    test_writer_indent2();
    ideal.machine.elements.runtime_util.end_test();
    ideal.machine.elements.runtime_util.start_test("test_plain_text.test_self_closing_tag");
    test_self_closing_tag();
    ideal.machine.elements.runtime_util.end_test();
    ideal.machine.elements.runtime_util.start_test("test_plain_text.test_writer_fragment");
    test_writer_fragment();
    ideal.machine.elements.runtime_util.end_test();
    ideal.machine.elements.runtime_util.start_test("test_plain_text.test_underline_tag");
    test_underline_tag();
    ideal.machine.elements.runtime_util.end_test();
    ideal.machine.elements.runtime_util.start_test("test_plain_text.test_underline2_tag");
    test_underline2_tag();
    ideal.machine.elements.runtime_util.end_test();
    ideal.machine.elements.runtime_util.start_test("test_plain_text.test_two_underlines");
    test_two_underlines();
    ideal.machine.elements.runtime_util.end_test();
    ideal.machine.elements.runtime_util.start_test("test_plain_text.test_blank_line");
    test_blank_line();
    ideal.machine.elements.runtime_util.end_test();
  }
  public static final base_string FOO = (base_string) new base_string("foo");
  public static final base_string BAR = (base_string) new base_string("bar");
  public static final base_string BAZ = (base_string) new base_string("baz");
  public static final base_string WYZZY = (base_string) new base_string("wyzzy");
  public void test_writer_trivial() {
    final string_writer the_writer = new string_writer();
    final plain_formatter the_formatter = new plain_formatter(the_writer);
    the_formatter.write(base_element.make(text_library.P, test_plain_text.FOO));
    assert ideal.machine.elements.runtime_util.values_equal(new base_string("foo\n"), the_writer.elements());
  }
  public void test_writer_indent0() {
    final string_writer the_writer = new string_writer();
    final plain_formatter the_formatter = new plain_formatter(the_writer);
    the_formatter.write(base_element.make(text_library.P, test_plain_text.FOO));
    the_formatter.write(base_element.make(text_library.INDENT, test_plain_text.BAR));
    assert ideal.machine.elements.runtime_util.values_equal(new base_string("foo\n  bar\n"), the_writer.elements());
  }
  public void test_writer_indent1() {
    final string_writer the_writer = new string_writer();
    final plain_formatter the_formatter = new plain_formatter(the_writer);
    the_formatter.write(base_element.make(text_library.P, test_plain_text.FOO));
    final text_element bar = base_element.make(text_library.P, test_plain_text.BAR);
    final text_element baz = base_element.make(text_library.P, test_plain_text.BAZ);
    the_formatter.write(base_element.make(text_library.INDENT, base_list_text_node.make(bar, baz)));
    the_formatter.write(base_element.make(text_library.P, test_plain_text.WYZZY));
    assert ideal.machine.elements.runtime_util.values_equal(new base_string("foo\n  bar\n  baz\nwyzzy\n"), the_writer.elements());
  }
  public void test_writer_indent2() {
    final string_writer the_writer = new string_writer();
    final plain_formatter the_formatter = new plain_formatter(the_writer);
    the_formatter.write(base_element.make(text_library.P, test_plain_text.FOO));
    the_formatter.write(base_element.make(text_library.INDENT, (base_string) new base_string("bar\nbaz")));
    the_formatter.write(base_element.make(text_library.P, test_plain_text.WYZZY));
    assert ideal.machine.elements.runtime_util.values_equal(new base_string("foo\n  bar\n  baz\nwyzzy\n"), the_writer.elements());
  }
  public void test_self_closing_tag() {
    final string_writer the_writer = new string_writer();
    final plain_formatter the_formatter = new plain_formatter(the_writer);
    the_formatter.write(test_plain_text.FOO);
    the_formatter.write(base_element.make(text_library.BR, text_library.CLEAR, (base_string) new base_string("all"), null));
    the_formatter.write((base_string) new base_string("bar\n"));
    assert ideal.machine.elements.runtime_util.values_equal(new base_string("foo\nbar\n"), the_writer.elements());
  }
  public void test_writer_fragment() {
    final string_writer the_writer = new string_writer();
    final plain_formatter the_formatter = new plain_formatter(the_writer);
    final text_entity fragment = new text_entity(text_library.IDEAL_TEXT, new base_string("*"), new base_string("&middot;"));
    the_formatter.write((base_string) new base_string("one"));
    the_formatter.write(fragment);
    the_formatter.write((base_string) new base_string("two"));
    assert ideal.machine.elements.runtime_util.values_equal(new base_string("one*two"), the_writer.elements());
  }
  public void test_underline_tag() {
    final string_writer the_writer = new string_writer();
    final plain_formatter the_formatter = new plain_formatter(the_writer);
    the_formatter.write((base_string) new base_string("hello "));
    final base_string world_string = (base_string) new base_string("world");
    final text_element br = base_element.make(text_library.BR, null);
    the_formatter.write(base_element.make(text_library.UNDERLINE, base_list_text_node.make(world_string, br, test_plain_text.FOO)));
    the_formatter.write((base_string) new base_string(" bar"));
    the_formatter.write(base_element.make(text_library.BR, null));
    assert ideal.machine.elements.runtime_util.values_equal(new base_string("hello world\n      ^^^^^\nfoo bar\n^^^\n"), the_writer.elements());
  }
  public void test_underline2_tag() {
    final string_writer the_writer = new string_writer();
    final plain_formatter the_formatter = new plain_formatter(the_writer);
    the_formatter.write((base_string) new base_string("hello "));
    final base_string world_string = (base_string) new base_string("world");
    final text_element br = base_element.make(text_library.BR, null);
    the_formatter.write(base_element.make(text_library.UNDERLINE2, base_list_text_node.make(world_string, br, test_plain_text.FOO)));
    the_formatter.write((base_string) new base_string(" bar"));
    the_formatter.write(base_element.make(text_library.BR, null));
    assert ideal.machine.elements.runtime_util.values_equal(new base_string("hello world\n      -----\nfoo bar\n---\n"), the_writer.elements());
  }
  public void test_two_underlines() {
    final string_writer the_writer = new string_writer();
    final plain_formatter the_formatter = new plain_formatter(the_writer);
    final text_element hi = base_element.make(text_library.UNDERLINE, (base_string) new base_string("hi"));
    final text_element mid = new base_element(text_library.UNDERLINE2, new list_dictionary<attribute_id, string>(), base_list_text_node.make((base_string) new base_string("start "), hi, (base_string) new base_string(" end")));
    the_formatter.write(text_util.join((base_string) new base_string("foo "), mid, (base_string) new base_string(" bar")));
    the_formatter.write(base_element.make(text_library.BR, null));
    assert ideal.machine.elements.runtime_util.values_equal(new base_string("foo start hi end bar\n    ------^^----\n"), the_writer.elements());
  }
  public void test_blank_line() {
    final string_writer the_writer = new string_writer();
    final plain_formatter the_formatter = new plain_formatter(the_writer);
    the_formatter.write(base_element.make(text_library.DIV, test_plain_text.FOO));
    the_formatter.write(new base_element(text_library.BR));
    the_formatter.write(base_element.make(text_library.DIV, (base_string) new base_string("bar")));
    assert ideal.machine.elements.runtime_util.values_equal(new base_string("foo\n\nbar\n"), the_writer.elements());
  }
}
