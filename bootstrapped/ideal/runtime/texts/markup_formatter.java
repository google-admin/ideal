// Autogenerated from runtime/texts/markup_formatter.i

package ideal.runtime.texts;

import ideal.library.elements.*;
import ideal.library.texts.*;
import ideal.runtime.elements.*;
import ideal.library.channels.output;
import ideal.machine.channels.string_writer;
import ideal.machine.elements.runtime_util;

import javax.annotation.Nullable;

public class markup_formatter extends text_formatter {
  public static final string OPEN_START_TAG = new base_string("<");
  public static final string OPEN_END_TAG = new base_string("</");
  public static final string ATTRIBUTE_SEPARATOR = new base_string(" ");
  public static final string ATTRIBUTE_START = new base_string("=\'");
  public static final string ATTRIBUTE_END = new base_string("\'");
  public static final string CLOSE_TAG = new base_string(">");
  public static final string CLOSE_SELF_CLOSING_TAG = new base_string(" />");
  public static final string DEFAULT_INDENT = new base_string(" ");
  public markup_formatter(final output<Character> out, final string spaces) {
    super(out, spaces);
  }
  public markup_formatter(final output<Character> out) {
    this(out, markup_formatter.DEFAULT_INDENT);
  }
  public @Override Void process_string(final string s) {
    this.write_escaped(s);
    return null;
  }
  public @Override Void process_element(final text_element element) {
    final boolean is_block = text_util.is_block(element);
    final immutable_dictionary<attribute_id, string> attributes = element.attributes();
    final @Nullable text_fragment children = element.children();
    if (children == null) {
      this.write_self_closing_tag(element, attributes);
      if (is_block) {
        this.write_newline();
      }
    } else {
      if (is_block) {
        if (!this.first) {
          this.write_newline();
        }
        if (this.indent > 0) {
          this.do_indent();
        }
        this.write_start_tag(element, attributes);
        this.write_newline();
        this.do_indent();
      } else {
        this.write_start_tag(element, attributes);
      }
      this.process(children);
      if (is_block) {
        if (!this.first) {
          this.write_newline();
        }
        this.do_unindent();
        this.write_end_tag(element);
        this.write_newline();
        if (this.indent > 0) {
          this.do_unindent();
        }
      } else {
        this.write_end_tag(element);
      }
    }
    return null;
  }
  private void do_indent() {
    this.indent += 1;
  }
  private void do_unindent() {
    final int new_indent = this.indent - 1;
    assert new_indent >= 0;
    this.indent = new_indent;
  }
  public @Override Void process_special(final special_text t) {
    this.write_string(t.to_markup());
    return null;
  }
  private void write_start_tag(final text_element element, final readonly_dictionary<attribute_id, string> attributes) {
    this.write_string(markup_formatter.OPEN_START_TAG);
    this.write_escaped(element.get_id().short_name());
    this.write_tag_attributes(attributes);
    this.write_string(markup_formatter.CLOSE_TAG);
  }
  private void write_end_tag(final text_element element) {
    this.write_string(markup_formatter.OPEN_END_TAG);
    this.write_escaped(element.get_id().short_name());
    this.write_string(markup_formatter.CLOSE_TAG);
  }
  private void write_self_closing_tag(final text_element element, final readonly_dictionary<attribute_id, string> attributes) {
    this.write_string(markup_formatter.OPEN_START_TAG);
    this.write_escaped(element.get_id().short_name());
    this.write_tag_attributes(attributes);
    this.write_string(markup_formatter.CLOSE_SELF_CLOSING_TAG);
  }
  private void write_tag_attributes(final readonly_dictionary<attribute_id, string> attributes) {
    {
      final readonly_list<dictionary.entry<attribute_id, string>> attribute_list = attributes.elements();
      for (int attribute_index = 0; attribute_index < attribute_list.size(); attribute_index += 1) {
        final dictionary.entry<attribute_id, string> attribute = attribute_list.get(attribute_index);
        this.write_string(markup_formatter.ATTRIBUTE_SEPARATOR);
        this.write_escaped(attribute.key().short_name());
        this.write_string(markup_formatter.ATTRIBUTE_START);
        this.write_escaped(attribute.value());
        this.write_string(markup_formatter.ATTRIBUTE_END);
      }
    }
  }
  private void write_escaped(final string s) {
    this.write_string(runtime_util.escape_markup(s));
  }
}
