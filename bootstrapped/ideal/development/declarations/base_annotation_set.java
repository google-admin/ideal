// Autogenerated from development/declarations/base_annotation_set.i

package ideal.development.declarations;

import ideal.library.elements.*;
import ideal.library.reflections.*;
import ideal.runtime.elements.*;
import ideal.runtime.logs.*;
import ideal.development.elements.*;
import ideal.development.names.*;
import ideal.development.comments.*;
import ideal.development.modifiers.*;
import ideal.development.comments.documentation;

import javax.annotation.Nullable;

public class base_annotation_set extends debuggable implements annotation_set {
  private final access_modifier the_access_level;
  private final @Nullable variance_modifier the_variance;
  private final immutable_set<modifier_kind> the_modifiers;
  private final @Nullable documentation the_documentation2;
  private final immutable_list<origin> the_origins;
  public @Override access_modifier access_level() {
    return this.the_access_level;
  }
  public @Override @Nullable variance_modifier variance() {
    return this.the_variance;
  }
  public @Override boolean has(final modifier_kind the_kind) {
    assert the_kind != null;
    return this.the_modifiers.contains(the_kind);
  }
  public @Override @Nullable documentation the_documentation() {
    return this.the_documentation2;
  }
  public @Override @Nullable origin deeper_origin() {
    if (this.the_origins.is_empty()) {
      return null;
    } else {
      return this.the_origins.first();
    }
  }
  public @Override action to_action() {
    utilities.panic(new base_string("base_annotation_set.to_action not implemented"));
    return null;
  }
  public immutable_list<origin> origins() {
    return this.the_origins;
  }
  public readonly_list<modifier_kind> modifiers() {
    return this.the_modifiers.elements();
  }
  public @Override readonly_list<analyzable> children() {
    return new empty<analyzable>();
  }
  public @Override boolean has_errors() {
    return false;
  }
  public @Override base_annotation_set analyze() {
    return this;
  }
  public @Override base_annotation_set specialize(final specialization_context context, final principal_type new_parent) {
    return this;
  }
  public base_annotation_set update_documentation(final @Nullable documentation new_documentation) {
    return new base_annotation_set(this.the_access_level, this.variance(), this.the_modifiers, new_documentation, this.the_origins);
  }
  public base_annotation_set(final access_modifier the_access_level, final @Nullable variance_modifier the_variance, final immutable_set<modifier_kind> the_modifiers, final @Nullable documentation the_documentation2, final immutable_list<origin> the_origins) {
    this.the_access_level = the_access_level;
    this.the_variance = the_variance;
    this.the_modifiers = the_modifiers;
    this.the_documentation2 = the_documentation2;
    this.the_origins = the_origins;
  }
}
