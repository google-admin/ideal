/*
 * Copyright 2014-2025 The Ideal Authors. All rights reserved.
 *
 * Use of this source code is governed by a BSD-style
 * license that can be found in the LICENSE file or at
 * https://theideal.org/license/
 */

package ideal.development.analyzers;

import ideal.library.elements.*;
import javax.annotation.Nullable;
import ideal.runtime.elements.*;
import ideal.runtime.logs.*;
import ideal.development.elements.*;
import ideal.development.actions.*;
import ideal.development.constructs.*;
import ideal.development.comments.*;
import ideal.development.notifications.*;
import ideal.development.names.*;
import ideal.development.types.*;
import ideal.development.flavors.*;
import ideal.development.declarations.*;
import ideal.development.modifiers.*;
import ideal.development.values.*;

public abstract class declaration_analyzer<C extends origin> extends multi_pass_analyzer<C>
    implements declaration {

  private @Nullable annotation_set the_annotation_set;

  protected declaration_analyzer(C source, @Nullable principal_type parent,
        @Nullable analysis_context context) {
    super(source, parent, context);
  }

  protected declaration_analyzer(C source) {
    super(source);
  }

  public annotation_set annotations() {
    if (the_annotation_set == null) {
      utilities.panic("annotation_set uninitialized in " + this);
    }
    assert the_annotation_set != null;
    return the_annotation_set;
  }

  protected void set_annotations(annotation_set new_annotation_set) {
    assert new_annotation_set != null;
    the_annotation_set = new_annotation_set;
  }

  @Override
  protected analysis_result do_get_result() {
    return common_values.nothing(this);
  }

  public principal_type declared_in_type() {
    principal_type the_type = parent();

    while (the_type.short_name() == INSIDE_NAME) {
      the_type = the_type.get_parent();
    }

    if (static_declaration() && the_type instanceof parametrized_type) {
      the_type = ((parametrized_type) the_type).get_master();
    }

    return the_type;
  }

  protected boolean static_declaration() {
    return false;
  }

  protected kind outer_kind() {
    return declared_in_type().get_kind();
  }

  protected boolean is_static_declaration() {
    return annotations().has(general_modifier.static_modifier) ||
        declared_in_type().get_kind().default_profile() == flavor_profiles.nameonly_profile;
  }

  protected void process_annotations(readonly_list<annotation_construct> annotations,
      access_modifier default_access) {

    assert the_annotation_set == null;

    list<origin> origins = new base_list<origin>();
    @Nullable access_modifier access_level = null;
    @Nullable variance_modifier variance = null;
    set<modifier_kind> modifiers = new hash_set<modifier_kind>();
    @Nullable documentation the_documentation = null;

    for (int i = 0; i < annotations.size(); ++i) {
      annotation_construct the_annotation = annotations.get(i);

      if (the_annotation instanceof modifier_construct) {
        modifier_kind the_modifier_kind = ((modifier_construct) the_annotation).the_kind;
        if (the_modifier_kind instanceof access_modifier) {
          if (access_level == null) {
            access_level = (access_modifier) the_modifier_kind;
          } else {
            // duplicate modifier
            new base_notification(messages.duplicate_access, the_annotation).report();
          }
        } else if (the_modifier_kind instanceof variance_modifier) {
          if (variance == null) {
            variance = (variance_modifier) the_modifier_kind;
          } else {
            // duplicate modifier
            new base_notification(messages.duplicate_variance, the_annotation).report();
          }
        } else if (the_modifier_kind instanceof extension_kind) {
          // Skip extensions, and skip them in origins
          continue;
        } else {
          // TODO: on duplicates, report an error instead...
          //new base_notification(error_message, annotations.get(i)).report();
          assert !modifiers.contains(the_modifier_kind);

          modifiers.add(the_modifier_kind);
        }
      } else {
        assert the_annotation instanceof comment_construct;
        // TODO: Signal an error?  Merge documentation?
        assert the_documentation == null;
        the_documentation = (comment_construct) the_annotation;
      }

      origins.append(the_annotation);
    }

    if (access_level == null) {
      access_level = default_access;
    }

    the_annotation_set = new base_annotation_set(access_level, variance, modifiers.frozen_copy(),
        the_documentation, origins.frozen_copy());
  }

  protected @Nullable type_flavor process_flavor(
      @Nullable readonly_list<annotation_construct> post_annotations) {
    if (post_annotations == null) {
      return null;
    }

    // TODO: handle duplicate or out-of-context annotations
    for (int i = 0; i < post_annotations.size(); ++i) {
      annotation_construct the_annotation = post_annotations.get(i);
      if (the_annotation instanceof modifier_construct) {
        modifier_kind the_kind = ((modifier_construct) the_annotation).the_kind;
        // TODO: handle parameters, detect duplicate flavors...
        if (the_kind instanceof type_flavor) {
          return (type_flavor) the_kind;
        }
      }
    }

    return null;
  }
}
