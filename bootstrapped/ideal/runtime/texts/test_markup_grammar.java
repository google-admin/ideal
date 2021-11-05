// Autogenerated from runtime/texts/test_markup_grammar.i

package ideal.runtime.texts;

import ideal.library.elements.*;
import ideal.library.characters.*;
import ideal.library.texts.*;
import ideal.runtime.elements.*;
import ideal.library.channels.output;
import ideal.runtime.texts.text_library.*;
import ideal.machine.characters.unicode_handler;
import ideal.machine.channels.string_writer;

public class test_markup_grammar {
  public string error_message;
  public markup_grammar make_grammar() {
    final markup_grammar grammar = new markup_grammar(unicode_handler.instance);
    grammar.add_elements(text_library.HTML_ELEMENTS);
    grammar.add_attributes(text_library.HTML_ATTRIBUTES);
    grammar.add_entities(text_library.HTML_ENTITIES);
    grammar.complete();
    return grammar;
  }
  public void test_entity_ref() {
    final ideal.library.patterns.matcher<Character, special_text> entity_ref = this.make_grammar().entity_ref;
    assert entity_ref.call(new base_string("&lt;"));
    assert entity_ref.call(new base_string("&amp;"));
    assert entity_ref.call(new base_string("&bull;"));
    assert !entity_ref.call(new base_string("foo"));
    assert !entity_ref.call(new base_string("&foo"));
    assert !entity_ref.call(new base_string("foo;"));
    assert entity_ref.parse(new base_string("&lt;")) == text_library.LT;
    assert entity_ref.parse(new base_string("&gt;")) == text_library.GT;
    assert entity_ref.parse(new base_string("&apos;")) == text_library.APOS;
    assert entity_ref.parse(new base_string("&quot;")) == text_library.QUOT;
    assert entity_ref.parse(new base_string("&mdash;")) == text_library.MDASH;
    assert entity_ref.parse(new base_string("&nbsp;")) == text_library.NBSP;
  }
  public void test_attribute_value() {
    final markup_grammar grammar = this.make_grammar();
    final ideal.library.patterns.matcher<Character, string> quot_attr_value = grammar.quot_attr_value;
    assert quot_attr_value.call(new base_string("foo"));
    assert quot_attr_value.call(new base_string("*bar*"));
    assert quot_attr_value.call(new base_string("\'baz\'"));
    assert !quot_attr_value.call(new base_string("&lt;"));
    assert !quot_attr_value.call(new base_string("\"a"));
    final ideal.library.patterns.matcher<Character, string> apos_attr_value = grammar.apos_attr_value;
    assert apos_attr_value.call(new base_string("foo"));
    assert apos_attr_value.call(new base_string("*bar*"));
    assert apos_attr_value.call(new base_string("\"baz\""));
    assert !apos_attr_value.call(new base_string("&lt;"));
    assert !apos_attr_value.call(new base_string("\'a"));
    final ideal.library.patterns.matcher<Character, attribute_fragment> attribute_value_in_quot = grammar.attribute_value_in_quot;
    assert ideal.machine.elements.runtime_util.values_equal(attribute_value_in_quot.parse(new base_string("\"\"")).to_string(), new base_string(""));
    assert ideal.machine.elements.runtime_util.values_equal(attribute_value_in_quot.parse(new base_string("\"foo\"")).to_string(), new base_string("foo"));
    assert ideal.machine.elements.runtime_util.values_equal(attribute_value_in_quot.parse(new base_string("\"&lt;\"")).to_string(), new base_string("&lt;"));
    assert ideal.machine.elements.runtime_util.values_equal(attribute_value_in_quot.parse(new base_string("\"foo&lt;bar\"")).to_string(), new base_string("foo&lt;bar"));
    assert ideal.machine.elements.runtime_util.values_equal(attribute_value_in_quot.parse(new base_string("\"&quot;-&apos;\"")).to_string(), new base_string("&quot;-&apos;"));
    assert ideal.machine.elements.runtime_util.values_equal(attribute_value_in_quot.parse(new base_string("\"&lt;foo&gt;bar\'baz\"")).to_string(), new base_string("&lt;foo&gt;bar\'baz"));
    final ideal.library.patterns.matcher<Character, attribute_fragment> attribute_value_in_apos = grammar.attribute_value_in_apos;
    assert ideal.machine.elements.runtime_util.values_equal(attribute_value_in_apos.parse(new base_string("\'\'")).to_string(), new base_string(""));
    assert ideal.machine.elements.runtime_util.values_equal(attribute_value_in_apos.parse(new base_string("\'foo\'")).to_string(), new base_string("foo"));
    assert ideal.machine.elements.runtime_util.values_equal(attribute_value_in_apos.parse(new base_string("\'&lt;\'")).to_string(), new base_string("&lt;"));
    assert ideal.machine.elements.runtime_util.values_equal(attribute_value_in_apos.parse(new base_string("\'foo&lt;bar\'")).to_string(), new base_string("foo&lt;bar"));
    assert ideal.machine.elements.runtime_util.values_equal(attribute_value_in_apos.parse(new base_string("\'&quot;-&apos;\'")).to_string(), new base_string("&quot;-&apos;"));
    assert ideal.machine.elements.runtime_util.values_equal(attribute_value_in_apos.parse(new base_string("\'&lt;foo&gt;bar\"baz\'")).to_string(), new base_string("&lt;foo&gt;bar\"baz"));
  }
  public void test_attribute() {
    final ideal.library.patterns.matcher<Character, attribute_state> attribute = this.make_grammar().attribute;
    assert attribute.call(new base_string("id = \'68\'"));
    assert attribute.call(new base_string("href = \"https://theideal.org/\""));
    assert attribute.call(new base_string("clear=\'all\'"));
    assert !attribute.call(new base_string("<html>"));
    assert !attribute.call(new base_string("foo"));
    assert !attribute.call(new base_string("bar ="));
    assert !attribute.call(new base_string("&lt;name&gt; = \'value\'"));
    final attribute_state attribute0 = attribute.parse(new base_string("id = \'68\'"));
    assert attribute0.id == text_library.ID;
    assert ideal.machine.elements.runtime_util.values_equal(((string) attribute0.value), new base_string("68"));
    final attribute_state attribute1 = attribute.parse(new base_string("href = \"https://theideal.org/\""));
    assert attribute1.id == text_library.HREF;
    assert ideal.machine.elements.runtime_util.values_equal(((string) attribute1.value), new base_string("https://theideal.org/"));
  }
  public void test_empty_element() {
    final ideal.library.patterns.matcher<Character, text_element> empty_element = this.make_grammar().empty_element;
    assert empty_element.call(new base_string("<html/>"));
    assert empty_element.call(new base_string("<body class=\"foo\" />"));
    assert !empty_element.call(new base_string("<html>"));
    assert !empty_element.call(new base_string("bar"));
    assert !empty_element.call(new base_string("&lt;html&gt;"));
    final text_element element0 = empty_element.parse(new base_string("<html/>"));
    assert element0.get_id() == text_library.HTML;
    assert element0.children() == null;
    assert element0.attributes().is_empty();
    final text_element element1 = empty_element.parse(new base_string("<body class=\"foo\" />"));
    assert element1.get_id() == text_library.BODY;
    assert element1.children() == null;
    assert ideal.machine.elements.runtime_util.values_equal(element1.attributes().size(), 1);
    assert element1.attributes().elements().get(0).key() == text_library.CLASS;
    assert ideal.machine.elements.runtime_util.values_equal(element1.attributes().elements().get(0).value(), new base_string("foo"));
    final text_element element2 = empty_element.parse(new base_string("<a class=\'foo\' href=\'https://theideal.org/\'/>"));
    assert element2.get_id() == text_library.A;
    assert element2.children() == null;
    assert ideal.machine.elements.runtime_util.values_equal(element2.attributes().size(), 2);
    final immutable_list<dictionary.entry<attribute_id, attribute_fragment>> attributes = element2.attributes().elements();
    assert attributes.get(0).key() == text_library.CLASS;
    assert ideal.machine.elements.runtime_util.values_equal(attributes.get(0).value(), new base_string("foo"));
    assert attributes.get(1).key() == text_library.HREF;
    assert ideal.machine.elements.runtime_util.values_equal(attributes.get(1).value(), new base_string("https://theideal.org/"));
  }
  public void test_content() {
    final ideal.library.patterns.matcher<Character, text_fragment> content = this.make_grammar().content;
    assert content.call(new base_string("  hello, world!"));
    assert content.call(new base_string("&lt;html&gt;"));
    assert !content.call(new base_string("<bar>"));
  }
  public void test_simple_parse() {
    final markup_grammar grammar = this.make_grammar();
    final ideal.library.patterns.matcher<Character, text_element> document_matcher = grammar.document_matcher;
    assert document_matcher.call(new base_string("<html>foo</html>"));
    assert document_matcher.call(new base_string("  <html>foo</html>  "));
    assert document_matcher.call(new base_string("  <html  >foo</html  >  "));
    assert document_matcher.call(new base_string("  <html  >Hello &amp; goodbye!</html  >  "));
    assert document_matcher.call(new base_string("  <html  />  "));
    assert document_matcher.call(new base_string("<html/>"));
    assert document_matcher.call(new base_string("  <html>Hello <em>world!</em></html>  "));
    assert document_matcher.call(new base_string("  <html><body ><p>Hello <em >world!</em ></p></body ></html>  "));
    assert document_matcher.call(new base_string("  <html><body > <p>Hello<br />world!</p> </body ></html>  "));
    assert document_matcher.call(new base_string("  <html><body > Hello &lt;world!&gt; </body ></html>  "));
    assert document_matcher.call(new base_string("<html><p class=\'klass\'>foo</p></html>"));
    assert document_matcher.call(new base_string("<html><a class = \'klass\' href = \'link\'>bar</a></html>"));
    assert document_matcher.call(new base_string("<html><p class = \'value\">==\' attr=\"foo\'\">foo</p></html>"));
    assert document_matcher.call(new base_string("<html><p class = \'***\' attr=\"baz\">foo</p></html>"));
    assert !document_matcher.call(new base_string(" no markup "));
    assert !document_matcher.call(new base_string("  <html>foo  "));
    assert !document_matcher.call(new base_string("  <html>foo<bar>  "));
    assert !document_matcher.call(new base_string("  <>foo  "));
    assert !document_matcher.call(new base_string("  &amp;<html>foo</html>  "));
    assert !document_matcher.call(new base_string("<html><p class=\'klass\">foo</p></html>"));
    assert !document_matcher.call(new base_string("<html><p class=\'klass\'>foo</p class=\"foo\"></html>"));
    assert !document_matcher.call(new base_string("<html foo= ><p class=\'klass\'>foo</p></html>"));
    assert !document_matcher.call(new base_string("<html foo=bar><p class=\'klass\'>foo</p></html>"));
    assert document_matcher.call(new base_string("  <abc>foo</def>  "));
    assert this.matches(document_matcher.parse(new base_string("  <html>foo</html>  ")), new base_string("<html>foo</html>"));
    assert this.matches(document_matcher.parse(new base_string("  <html  >Hello &amp; goodbye!</html  >  ")), new base_string("<html>Hello &amp; goodbye!</html>"));
    assert this.matches(document_matcher.parse(new base_string("  <html  />  ")), new base_string("<html />"));
    assert this.matches(document_matcher.parse(new base_string("  <html>Hello <em>world!</em></html>  ")), new base_string("<html>Hello <em>world!</em></html>"));
    assert this.matches(document_matcher.parse(new base_string("  <html><body > <p>Hello<br />world!</p> </body ></html>  ")), new base_string("<html><body> <p>Hello<br />world!</p> </body></html>"));
    assert this.matches(document_matcher.parse(new base_string("<html><p class=\'klass\'>foo</p></html>")), new base_string("<html><p class=\'klass\'>foo</p></html>"));
    assert this.matches(document_matcher.parse(new base_string("<html><p id=\'f&amp;f\'>foo</p></html>")), new base_string("<html><p id=\'f&amp;f\'>foo</p></html>"));
    assert this.matches(document_matcher.parse(new base_string("<html><a class = \'klass\' href = \'link\'>bar</a></html>")), new base_string("<html><a class=\'klass\' href=\'link\'>bar</a></html>"));
    assert this.matches(document_matcher.parse(new base_string("<html><p class = \'value\">==\' id=\"foo\'\">foo</p></html>")), new base_string("<html><p class=\'value&quot;&gt;==\' id=\'foo&apos;\'>foo</p></html>"));
    assert this.matches(document_matcher.parse(new base_string("<html><p class = \'***\' id=\"baz\">foo</p></html>")), new base_string("<html><p class=\'***\' id=\'baz\'>foo</p></html>"));
  }
  public void test_parse_errors() {
    final markup_grammar grammar = this.make_grammar();
    final markup_parser parser = new markup_parser(grammar, new procedure1<Void, string>() {
      public @Override Void call(string first) {
        test_markup_grammar.this.report_error(first);
        return null;
      }
    });
    assert this.matches_with_error(parser.parse(new base_string("<html>&bug;</html>")), new base_string("<html>&_error_;</html>"), new base_string("Unrecognized entity: bug"));
    assert this.matches_with_error(parser.parse(new base_string("<html><foo>Hello!</foo></html>")), new base_string("<html><_error_>Hello!</_error_></html>"), new base_string("Unrecognized element name: foo"));
    assert this.matches_with_error(parser.parse(new base_string("<html><b attr=\"value\">Hello!</b></html>")), new base_string("<html><b _error_=\'value\'>Hello!</b></html>"), new base_string("Unrecognized attribute name: attr"));
    assert this.matches_with_error(parser.parse(new base_string("<html><a>Hello!</b></html>")), new base_string("<html><a>Hello!</a></html>"), new base_string("Mismatched element name: start a, end b"));
  }
  private void report_error(final string error_message) {
    this.error_message = error_message;
  }
  private boolean matches_with_error(final text_element the_text_element, final string expected, final string expected_error) {
    return this.matches(the_text_element, expected) && ideal.machine.elements.runtime_util.values_equal(this.error_message, expected_error);
  }
  private boolean matches(final text_element the_text_element, final string expected) {
    final string_writer the_writer = new string_writer();
    final markup_formatter the_formatter = new markup_formatter(the_writer, new base_string(""), false);
    the_formatter.write(the_text_element);
    return ideal.machine.elements.runtime_util.values_equal(the_writer.elements(), expected);
  }
  public test_markup_grammar() { }
  public void run_all_tests() {
    ideal.machine.elements.runtime_util.start_test(new base_string("test_markup_grammar.test_entity_ref"));
    this.test_entity_ref();
    ideal.machine.elements.runtime_util.end_test();
    ideal.machine.elements.runtime_util.start_test(new base_string("test_markup_grammar.test_attribute_value"));
    this.test_attribute_value();
    ideal.machine.elements.runtime_util.end_test();
    ideal.machine.elements.runtime_util.start_test(new base_string("test_markup_grammar.test_attribute"));
    this.test_attribute();
    ideal.machine.elements.runtime_util.end_test();
    ideal.machine.elements.runtime_util.start_test(new base_string("test_markup_grammar.test_empty_element"));
    this.test_empty_element();
    ideal.machine.elements.runtime_util.end_test();
    ideal.machine.elements.runtime_util.start_test(new base_string("test_markup_grammar.test_content"));
    this.test_content();
    ideal.machine.elements.runtime_util.end_test();
    ideal.machine.elements.runtime_util.start_test(new base_string("test_markup_grammar.test_simple_parse"));
    this.test_simple_parse();
    ideal.machine.elements.runtime_util.end_test();
    ideal.machine.elements.runtime_util.start_test(new base_string("test_markup_grammar.test_parse_errors"));
    this.test_parse_errors();
    ideal.machine.elements.runtime_util.end_test();
  }
}
