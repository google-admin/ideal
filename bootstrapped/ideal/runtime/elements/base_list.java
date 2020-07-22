// Autogenerated from runtime/elements/base_list.i

package ideal.runtime.elements;

import ideal.library.elements.*;

public class base_list<element_type> extends base_readonly_list<element_type> implements list<element_type> {
  public base_list() { }
  public base_list(final element_type n1) {
    this();
    this.append(n1);
  }
  public base_list(final element_type n1, final element_type n2) {
    this();
    this.append(n1);
    this.append(n2);
  }
  public base_list(final element_type n1, final element_type n2, final element_type n3) {
    this();
    this.append(n1);
    this.append(n2);
    this.append(n3);
  }
  public base_list(final element_type n1, final element_type n2, final element_type n3, final element_type n4) {
    this();
    this.append(n1);
    this.append(n2);
    this.append(n3);
    this.append(n4);
  }
  public base_list(final element_type n1, final element_type n2, final element_type n3, final element_type n4, final element_type n5) {
    this();
    this.append(n1);
    this.append(n2);
    this.append(n3);
    this.append(n4);
    this.append(n5);
  }
  public base_list(final element_type n1, final element_type n2, final element_type n3, final element_type n4, final element_type n5, final element_type n6) {
    this();
    this.append(n1);
    this.append(n2);
    this.append(n3);
    this.append(n4);
    this.append(n5);
    this.append(n6);
  }
  public base_list(final element_type n1, final element_type n2, final element_type n3, final element_type n4, final element_type n5, final element_type n6, final element_type n7) {
    this();
    this.append(n1);
    this.append(n2);
    this.append(n3);
    this.append(n4);
    this.append(n5);
    this.append(n6);
    this.append(n7);
  }
  public base_list(final readonly_list<element_type> the_list) {
    this();
    this.append_all(the_list);
  }
  public @Override void clear() {
    if (this.is_not_empty()) {
      this.state = new base_readonly_list.list_state<element_type>();
    }
  }
  private base_readonly_list.list_state<element_type> writable_state() {
    if (!this.state.writable) {
      this.state = this.state.copy();
      assert this.state.writable;
    }
    return this.state;
  }
  public @Override void append(final element_type element) {
    this.writable_state().insert(this.size(), element);
  }
  public @Override void append_all(final readonly_list<element_type> new_elements) {
    this.writable_state().insert_all(this.size(), new_elements);
  }
  public @Override void prepend(final element_type element) {
    this.writable_state().insert(0, element);
  }
  public @Override reference<element_type> at(final int index) {
    assert index < this.state.size;
    return this.writable_state().the_elements.at(index);
  }
  public @Override element_type remove_last() {
    assert this.is_not_empty();
    final int last_index = this.size() - 1;
    assert last_index >= 0;
    final element_type result = this.state.the_elements.at(last_index).get();
    this.writable_state().clear(last_index, 1);
    return result;
  }
}
