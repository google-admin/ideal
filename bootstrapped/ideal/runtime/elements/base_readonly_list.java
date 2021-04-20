// Autogenerated from runtime/elements/base_readonly_list.i

package ideal.runtime.elements;

import ideal.library.elements.*;
import ideal.machine.elements.array;

public class base_readonly_list<element_type> implements readonly_list<element_type> {
  public static class parameters {
    public static final int default_size = 16;
  }
  protected static class list_state<element_type> {
    public boolean writable;
    public array<element_type> the_elements;
    public int size;
    public list_state(final int initial_size) {
      this.writable = true;
      this.the_elements = new array<element_type>(initial_size);
      this.size = 0;
    }
    public list_state() {
      this(base_readonly_list.parameters.default_size);
    }
    public list_state(final array<element_type> immutable_elements) {
      this.writable = false;
      this.the_elements = immutable_elements;
      this.size = immutable_elements.size;
    }
    public void reserve(final int reserve_size) {
      if (this.the_elements.size >= reserve_size) {
        return;
      }
      int new_size = this.the_elements.size * 2;
      if (new_size < reserve_size) {
        new_size = reserve_size;
      }
      final array<element_type> new_elements = new array<element_type>(new_size);
      this.the_elements.copy(0, new_elements, 0, this.size);
      this.the_elements = new_elements;
    }
    public void insert_all(final int index, final readonly_list<element_type> new_elements) {
      if (new_elements.is_empty()) {
        return;
      } else if (new_elements.size() == 1) {
        this.insert(index, new_elements.first());
        return;
      }
      assert this.writable;
      final int extra_size = new_elements.size();
      this.reserve_and_move(index, extra_size);
      final array<element_type> new_elements_array = ((base_readonly_list<element_type>) (base_readonly_list) new_elements).state.the_elements;
      new_elements_array.copy(0, this.the_elements, index, extra_size);
    }
    public void insert(final int index, final element_type element) {
      assert this.writable;
      this.reserve_and_move(index, 1);
      this.the_elements.set(index, element);
    }
    private void reserve_and_move(final int index, final int extra_size) {
      this.reserve(this.size + extra_size);
      if (index < this.size) {
        final int tail_size = this.size - index;
        assert tail_size >= 0;
        this.the_elements.move(index, index + extra_size, tail_size);
      }
      this.size += extra_size;
    }
    public void clear(final int begin, final int length) {
      if (begin + length < this.size) {
        this.the_elements.move(begin + length, begin, length);
      }
      final int new_size = this.size - length;
      assert new_size >= 0;
      this.size = new_size;
      this.the_elements.scrub(this.size, length);
    }
    public base_readonly_list.list_state<element_type> copy() {
      final base_readonly_list.list_state<element_type> new_state = new base_readonly_list.list_state<element_type>(this.size);
      this.the_elements.copy(0, new_state.the_elements, 0, this.size);
      new_state.size = this.size;
      return new_state;
    }
  }
  protected base_readonly_list.list_state<element_type> state;
  protected base_readonly_list() {
    this.state = new base_readonly_list.list_state<element_type>();
  }
  protected base_readonly_list(final base_readonly_list.list_state<element_type> state) {
    this.state = state;
  }
  public @Override int size() {
    return this.state.size;
  }
  public @Override boolean is_empty() {
    return this.state.size == 0;
  }
  public @Override boolean is_not_empty() {
    return this.state.size != 0;
  }
  public @Override element_type first() {
    assert this.is_not_empty();
    return this.state.the_elements.at(0).get();
  }
  public @Override element_type last() {
    assert this.is_not_empty();
    final int last_index = this.state.size - 1;
    assert last_index >= 0;
    return this.state.the_elements.at(last_index).get();
  }
  public @Override element_type get(final int index) {
    assert index < this.state.size;
    return this.state.the_elements.at(index).get();
  }
  public @Override immutable_list<element_type> elements() {
    return this.frozen_copy();
  }
  public @Override immutable_list<element_type> frozen_copy() {
    return new base_immutable_list<element_type>(this.state);
  }
  public @Override immutable_list<element_type> slice(final int begin, final int end) {
    assert begin >= 0 && end <= this.size();
    final int length = end - begin;
    assert length >= 0;
    final base_readonly_list.list_state<element_type> slice_state = new base_readonly_list.list_state<element_type>(length);
    slice_state.size = length;
    this.state.the_elements.copy(begin, slice_state.the_elements, 0, length);
    return new base_immutable_list<element_type>(slice_state);
  }
  public @Override immutable_list<element_type> skip(final int count) {
    return this.slice(count, this.size());
  }
}
