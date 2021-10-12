// Autogenerated from development/types/base_principal_type.i

package ideal.development.types;

import ideal.library.elements.*;
import ideal.library.reflections.*;
import ideal.runtime.elements.*;
import ideal.runtime.logs.*;
import ideal.development.elements.*;
import ideal.development.names.*;
import ideal.development.flavors.*;
import ideal.development.declarations.*;
import ideal.development.kinds.*;

import javax.annotation.Nullable;
import ideal.machine.annotations.dont_display;

public abstract class base_principal_type extends base_type implements principal_type {
  protected @Nullable flavor_profile the_flavor_profile;
  protected declaration_pass last_pass;
  private @dont_display @Nullable declaration the_declaration;
  protected base_principal_type(final @Nullable flavor_profile the_flavor_profile, final declaration_pass last_pass, final @Nullable declaration the_declaration) {
    this.the_flavor_profile = the_flavor_profile;
    this.last_pass = last_pass;
    this.the_declaration = the_declaration;
  }
  public @Override principal_type principal() {
    return this;
  }
  public @Override type_flavor get_flavor() {
    return flavor.nameonly_flavor;
  }
  public @Override boolean has_flavor_profile() {
    return this.the_flavor_profile != null;
  }
  public @Override flavor_profile get_flavor_profile() {
    if (this.the_flavor_profile == null) {
      utilities.panic(ideal.machine.elements.runtime_util.concatenate(ideal.machine.elements.runtime_util.concatenate(ideal.machine.elements.runtime_util.concatenate(new base_string("Unset profile in "), this), new base_string(" decl ")), this.the_declaration));
      return null;
    }
    return this.the_flavor_profile;
  }
  public @Override type get_flavored(final type_flavor flavor) {
    @Nullable flavor_profile profile = this.the_flavor_profile;
    if (profile == null) {
      profile = this.default_flavor_profile();
      this.the_flavor_profile = profile;
    }
    return base_type.do_get_flavored(this, profile.map(flavor));
  }
  public void set_flavor_profile(final flavor_profile the_flavor_profile) {
    assert this.the_flavor_profile == null;
    {
      final readonly_list<type_flavor> flavor_list = flavor.all_flavors;
      for (Integer flavor_index = 0; flavor_index < flavor_list.size(); flavor_index += 1) {
        final type_flavor flavor = flavor_list.get(flavor_index);
        if (!the_flavor_profile.supports(flavor)) {
          if (((type_flavor_impl) flavor).types.contains_key(this)) {
            utilities.panic(ideal.machine.elements.runtime_util.concatenate(ideal.machine.elements.runtime_util.concatenate(ideal.machine.elements.runtime_util.concatenate(new base_string("Already used "), flavor), new base_string(" of ")), this));
          }
        }
      }
    }
    this.the_flavor_profile = the_flavor_profile;
  }
  public declaration_pass get_pass() {
    return this.last_pass;
  }
  public final @Override @Nullable declaration get_declaration() {
    return this.the_declaration;
  }
  public void set_declaration(final declaration the_declaration) {
    assert this.the_declaration == null;
    assert the_declaration != null;
    this.the_declaration = the_declaration;
  }
  public void process_declaration(final declaration_pass pass) {
    if (pass.is_before(this.last_pass) || pass == this.last_pass) {
      return;
    }
    if (this.last_pass.is_before(declaration_pass.FLAVOR_PROFILE)) {
      this.do_declare(declaration_pass.FLAVOR_PROFILE);
    }
    if (pass.is_after(declaration_pass.FLAVOR_PROFILE) && this.last_pass.is_before(declaration_pass.TYPES_AND_PROMOTIONS)) {
      this.do_declare(declaration_pass.TYPES_AND_PROMOTIONS);
    }
    if (pass == declaration_pass.METHODS_AND_VARIABLES && this.last_pass.is_before(declaration_pass.METHODS_AND_VARIABLES)) {
      this.do_declare(declaration_pass.METHODS_AND_VARIABLES);
    }
  }
  protected final void do_declare(final declaration_pass pass) {
    assert ideal.machine.elements.runtime_util.values_equal(pass.ordinal(), this.last_pass.ordinal() + 1);
    this.last_pass = pass;
    this.do_declare_actual(pass);
  }
  protected void do_declare_actual(final declaration_pass pass) {
    assert pass != declaration_pass.NONE;
    final type_declaration_context the_context = this.get_context();
    assert the_context != null;
    the_context.declare_type(this, pass);
  }
  public abstract flavor_profile default_flavor_profile();
  public final @Override string to_string() {
    return this.describe(type_format.FULL);
  }
}
