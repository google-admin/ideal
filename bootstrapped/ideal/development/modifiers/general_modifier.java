// Autogenerated from development/modifiers/general_modifier.i

package ideal.development.modifiers;

import ideal.library.elements.*;
import ideal.runtime.elements.*;
import ideal.runtime.logs.*;
import ideal.development.elements.*;
import ideal.development.names.*;

public class general_modifier {
  public static final modifier_kind static_modifier = new base_modifier_kind(new base_string("static"));
  public static final modifier_kind final_modifier = new base_modifier_kind(new base_string("final"));
  public static final modifier_kind pure_modifier = new base_modifier_kind(new base_string("pure"));
  public static final modifier_kind var_modifier = new base_modifier_kind(new base_string("var"));
  public static final modifier_kind abstract_modifier = new base_modifier_kind(new base_string("abstract"));
  public static final modifier_kind implicit_modifier = new base_modifier_kind(new base_string("implicit"));
  public static final modifier_kind explicit_modifier = new base_modifier_kind(new base_string("explicit"));
  public static final modifier_kind synthetic_modifier = new base_modifier_kind(new base_string("synthetic"));
  public static final modifier_kind varargs_modifier = new base_modifier_kind(new base_string("varargs"));
  public static final modifier_kind mutable_var_modifier = new base_modifier_kind(new base_string("mutable_var"));
  public static final modifier_kind override_modifier = new base_modifier_kind(new base_string("override"));
  public static final modifier_kind overload_modifier = new base_modifier_kind(new base_string("overload"));
  public static final modifier_kind implement_modifier = new base_modifier_kind(new base_string("implement"));
  public static final modifier_kind noreturn_modifier = new base_modifier_kind(new base_string("noreturn"));
  public static final modifier_kind test_case_modifier = new base_modifier_kind(new base_string("test_case"));
  public static final modifier_kind dont_display_modifier = new base_modifier_kind(new base_string("dont_display"));
  public static final modifier_kind synchronized_modifier = new base_modifier_kind(new base_string("synchronized"));
  public static final modifier_kind volatile_modifier = new base_modifier_kind(new base_string("volatile"));
  public static final modifier_kind transient_modifier = new base_modifier_kind(new base_string("transient"));
  public static final modifier_kind native_modifier = new base_modifier_kind(new base_string("native"));
  public static final modifier_kind nullable_modifier = new base_modifier_kind(new base_string("nullable"));
  public static final set<modifier_kind> supported_by_java = new hash_set<modifier_kind>();
  public static final set<modifier_kind> java_annotations = new hash_set<modifier_kind>();
  public static final set<modifier_kind> supported_by_javascript = new hash_set<modifier_kind>();
  static {
    general_modifier.supported_by_java.add_all(new base_immutable_list<modifier_kind>(new ideal.machine.elements.array<modifier_kind>(new modifier_kind[]{ access_modifier.public_modifier, access_modifier.protected_modifier, access_modifier.private_modifier, general_modifier.static_modifier, general_modifier.final_modifier, general_modifier.abstract_modifier, general_modifier.synchronized_modifier, general_modifier.volatile_modifier, general_modifier.transient_modifier, general_modifier.native_modifier })));
    general_modifier.java_annotations.add_all(new base_immutable_list<modifier_kind>(new ideal.machine.elements.array<modifier_kind>(new modifier_kind[]{ general_modifier.override_modifier, general_modifier.nullable_modifier, general_modifier.dont_display_modifier })));
    general_modifier.supported_by_java.add_all(general_modifier.java_annotations);
    general_modifier.supported_by_javascript.add_all(new base_immutable_list<modifier_kind>(new ideal.machine.elements.array<modifier_kind>(new modifier_kind[]{ general_modifier.var_modifier })));
  }
}
