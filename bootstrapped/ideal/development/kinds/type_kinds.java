// Autogenerated from development/kinds/type_kinds.i

package ideal.development.kinds;

import ideal.library.elements.*;
import ideal.runtime.elements.*;
import ideal.runtime.logs.*;
import ideal.development.elements.*;
import ideal.development.names.*;
import ideal.development.flavors.*;
import static ideal.development.flavors.flavor_profiles.*;

public class type_kinds {
  public static final base_kind block_kind = new base_kind(new base_string("block"), flavor_profiles.nameonly_profile, false);
  public static final base_kind class_kind = new base_kind(new base_string("class"), flavor_profiles.mutable_profile, true);
  public static final base_kind concept_kind = new base_kind(new base_string("concept"), flavor_profiles.mutable_profile, false);
  public static final base_kind datatype_kind = new base_kind(new base_string("datatype"), flavor_profiles.mutable_profile, true);
  public static final base_kind enum_kind = new base_kind(new base_string("enum"), flavor_profiles.deeply_immutable_profile, true);
  public static final base_kind interface_kind = new base_kind(new base_string("interface"), flavor_profiles.mutable_profile, false);
  public static final base_kind module_kind = new base_kind(new base_string("module"), flavor_profiles.nameonly_profile, false);
  public static final base_kind namespace_kind = new base_kind(new base_string("namespace"), flavor_profiles.nameonly_profile, false);
  public static final base_kind package_kind = new base_kind(new base_string("package"), flavor_profiles.nameonly_profile, false);
  public static final base_kind program_kind = new base_kind(new base_string("program"), flavor_profiles.mutable_profile, false);
  public static final base_kind html_content_kind = new base_kind(new base_string("html_content"), flavor_profiles.nameonly_profile, false);
  public static final base_kind singleton_kind = new base_kind(new base_string("singleton"), flavor_profiles.deeply_immutable_profile, false);
  public static final simple_name INSTANCE_NAME = simple_name.make(new base_string("instance"));
  public static final base_kind project_kind = new base_kind(new base_string("project"), flavor_profiles.nameonly_profile, false);
  public static final base_kind service_kind = new base_kind(new base_string("service"), flavor_profiles.mutable_profile, false);
  public static final base_kind world_kind = new base_kind(new base_string("world"), flavor_profiles.mutable_profile, false);
  public static final base_kind test_suite_kind = new base_kind(new base_string("test_suite"), flavor_profiles.mutable_profile, true);
  public static final base_kind reference_kind = new base_kind(new base_string("reference_kind"), flavor_profiles.mutable_profile, false);
  public static final base_kind procedure_kind = new base_kind(new base_string("procedure_kind"), flavor_profiles.immutable_profile, false);
  public static final base_kind union_kind = new base_kind(new base_string("union_kind"), flavor_profiles.mutable_profile, false);
  public static final base_kind type_alias_kind = new base_kind(new base_string("type_alias_kind"), flavor_profiles.mutable_profile, false);
}
