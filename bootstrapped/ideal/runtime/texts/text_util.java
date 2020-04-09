// Autogenerated from isource/runtime/texts/text_util.i

package ideal.runtime.texts;

import ideal.library.elements.*;
import ideal.library.texts.*;
import ideal.runtime.elements.*;
import ideal.library.channels.output;
import ideal.machine.channels.string_writer;

import javax.annotation.Nullable;

public class text_util {
  public final static text_fragment EMPTY_FRAGMENT = new base_list_text_node(new empty<text_node>());
  public static boolean is_indent(final text_element element) {
    return element.get_id() == text_library.INDENT;
  }
  public static boolean is_block(final text_element element) {
    final element_id id = element.get_id();
    return is_indent(element) || id == text_library.HTML || id == text_library.HEAD || id == text_library.BODY || id == text_library.TITLE || id == text_library.LINK || id == text_library.P || id == text_library.DIV || id == text_library.H1 || id == text_library.H2 || id == text_library.TABLE || id == text_library.TR || id == text_library.TH || id == text_library.TD || id == text_library.BR;
  }
  public static immutable_list<text_node> make_singleton(final text_node element) {
    final base_list<text_node> the_list = new base_list<text_node>();
    the_list.append(element);
    return the_list.frozen_copy();
  }
  public static immutable_list<text_node> to_list(final @Nullable text_fragment fragment) {
    if (fragment == null) {
      return new empty<text_node>();
    } else if (fragment instanceof text_node) {
      return make_singleton(((text_node) fragment));
    } else if (fragment instanceof list_text_node) {
      return ((list_text_node) fragment).nodes();
    } else {
      utilities.panic(ideal.machine.elements.runtime_util.concatenate(new base_string("Unknown type "), fragment));
      return null;
    }
  }
  public static text_fragment join(final readonly_list<text_fragment> fragments) {
    final base_list<text_node> nodes = new base_list<text_node>();
    for (int i = 0; i < fragments.size(); i += 1) {
      append(nodes, fragments.get(i));
    }
    return to_fragment(nodes);
  }
  public static text_fragment join(final text_fragment first, final text_fragment second) {
    final base_list<text_node> nodes = new base_list<text_node>();
    append(nodes, first);
    append(nodes, second);
    return to_fragment(nodes);
  }
  public static text_fragment join(final text_fragment first, final text_fragment second, final text_fragment third) {
    final base_list<text_node> nodes = new base_list<text_node>();
    append(nodes, first);
    append(nodes, second);
    append(nodes, third);
    return to_fragment(nodes);
  }
  private static void append(final list<text_node> nodes, final text_fragment fragment) {
    if (fragment instanceof string_text_node) {
      if (((string_text_node) fragment).is_empty()) {
        return;
      }
    }
    if (fragment instanceof text_node) {
      nodes.append(((text_node) fragment));
    } else {
      nodes.append_all(((list_text_node) fragment).nodes());
    }
  }
  private static text_fragment to_fragment(final readonly_list<text_node> nodes) {
    if (nodes.is_empty()) {
      return EMPTY_FRAGMENT;
    } else if (nodes.size() == 1) {
      return nodes.get(0);
    } else {
      return new base_list_text_node(nodes);
    }
  }
  public static string to_plain_text(final text_fragment text) {
    final string_writer the_writer = new string_writer();
    final plain_formatter formatter = new plain_formatter(the_writer);
    formatter.write(text);
    return the_writer.elements();
  }
  public static string to_markup_string(final text_fragment text) {
    final string_writer the_writer = new string_writer();
    final markup_formatter formatter = new markup_formatter(the_writer);
    formatter.write(text);
    return the_writer.elements();
  }
  public static text_element make_element(final element_id id, final readonly_list<text_node> children) {
    @Nullable text_fragment child_fragment;
    if (children != null) {
      child_fragment = new base_list_text_node(children);
    } else {
      child_fragment = null;
    }
    return new base_element(id, new list_dictionary<attribute_id, string>(), child_fragment);
  }
  public static text_element make_html_link(final text_fragment text, final string link_target) {
    return base_element.make(text_library.A, text_library.HREF, link_target, text);
  }
}
