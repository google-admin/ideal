// Autogenerated from development/names/operator.i

package ideal.development.names;

import ideal.library.elements.*;
import ideal.runtime.elements.*;
import ideal.runtime.logs.*;
import ideal.development.elements.*;
import ideal.development.symbols.base_symbols;
import static ideal.development.names.operator_type.*;

public class operator extends debuggable implements action_name {
  public final operator_type type;
  public final token_type name;
  public final simple_name alpha_name;
  private operator(final operator_type type, final token_type name, final string alpha_name) {
    this.type = type;
    this.name = name;
    this.alpha_name = simple_name.make(alpha_name);
  }
  private operator(final operator_type type, final operator primary, final token_type name, final string alpha_name) {
    this(type, name, alpha_name);
    assert ideal.machine.elements.runtime_util.values_equal(type, ASSIGNMENT);
    assert ideal.machine.elements.runtime_util.values_equal(name.name(), ideal.machine.elements.runtime_util.concatenate(primary.name.name(), new base_string("=")));
    assert ideal.machine.elements.runtime_util.values_equal(alpha_name.to_string(), ideal.machine.elements.runtime_util.concatenate(primary.alpha_name, new base_string("_assign")));
  }
  public simple_name symbol() {
    return alpha_name;
  }
  public string to_string() {
    return name_utilities.in_brackets(ideal.machine.elements.runtime_util.concatenate(ideal.machine.elements.runtime_util.concatenate(this.type, new base_string(" ")), name));
  }
  public static final operator ASSIGN = new operator(INFIX, punctuation.EQUALS, new base_string("assign"));
  public static final operator MULTIPLY = new operator(INFIX, punctuation.ASTERISK, new base_string("multiply"));
  public static final operator DIVIDE = new operator(INFIX, punctuation.SLASH, new base_string("divide"));
  public static final operator MODULO = new operator(INFIX, punctuation.PERCENT, new base_string("modulo"));
  public static final operator ADD = new operator(INFIX, punctuation.PLUS, new base_string("add"));
  public static final operator SUBTRACT = new operator(INFIX, punctuation.MINUS, new base_string("subtract"));
  public static final operator NEGATE = new operator(PREFIX, punctuation.MINUS, new base_string("negate"));
  public static final operator PRE_INCREMENT = new operator(PREFIX, punctuation.PLUS_PLUS, new base_string("pre_increment"));
  public static final operator CONCATENATE = new operator(INFIX, punctuation.PLUS_PLUS, new base_string("concatenate"));
  public static final operator EQUAL_TO = new operator(INFIX, punctuation.EQUALS_EQUALS, new base_string("equal_to"));
  public static final operator NOT_EQUAL_TO = new operator(INFIX, punctuation.EXCLAMATION_MARK_EQUALS, new base_string("not_equal_to"));
  public static final operator LESS = new operator(INFIX, punctuation.LESS_THAN, new base_string("less"));
  public static final operator GREATER = new operator(INFIX, punctuation.GREATER_THAN, new base_string("greater"));
  public static final operator LESS_EQUAL = new operator(INFIX, punctuation.LESS_THAN_EQUALS, new base_string("less_equal"));
  public static final operator GREATER_EQUAL = new operator(INFIX, punctuation.GREATER_THAN_EQUALS, new base_string("greater_equal"));
  public static final operator BIT_AND = new operator(INFIX, punctuation.AMPERSAND, new base_string("bit_and"));
  public static final operator XOR = new operator(INFIX, punctuation.CARET, new base_string("xor"));
  public static final operator BIT_OR = new operator(INFIX, punctuation.VERTICAL_BAR, new base_string("bit_or"));
  public static final operator LOGICAL_AND = new operator(INFIX, punctuation.AMPERSAND_AMPERSAND, new base_string("logical_and"));
  public static final operator LOGICAL_OR = new operator(INFIX, punctuation.VERTICAL_BAR_VERTICAL_BAR, new base_string("logical_or"));
  public static final operator LOGICAL_NOT = new operator(PREFIX, punctuation.EXCLAMATION_MARK, new base_string("logical_not"));
  public static final operator GENERAL_OR = new operator(INFIX, keyword.OR, new base_string("general_or"));
  public static final operator ADD_ASSIGN = new operator(ASSIGNMENT, ADD, punctuation.PLUS_EQUALS, new base_string("add_assign"));
  public static final operator SUBTRACT_ASSIGN = new operator(ASSIGNMENT, SUBTRACT, punctuation.MINUS_EQUALS, new base_string("subtract_assign"));
  public static final operator MULTIPLY_ASSIGN = new operator(ASSIGNMENT, MULTIPLY, punctuation.ASTERISK_EQUALS, new base_string("multiply_assign"));
  public static final operator CONCATENATE_ASSIGN = new operator(ASSIGNMENT, CONCATENATE, punctuation.PLUS_PLUS_EQUALS, new base_string("concatenate_assign"));
  public static final operator AS_OPERATOR = new operator(INFIX, keyword.AS, new base_string("as_operator"));
  public static final operator IS_OPERATOR = new operator(INFIX, keyword.IS, new base_string("is_operator"));
  public static final operator IS_NOT_OPERATOR = new operator(INFIX, keyword.IS_NOT, new base_string("is_not_operator"));
  public static final operator ALLOCATE = new operator(PREFIX, keyword.NEW, new base_string("allocate"));
}
