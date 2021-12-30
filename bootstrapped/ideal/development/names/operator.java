// Autogenerated from development/names/operator.i

package ideal.development.names;

import ideal.library.elements.*;
import ideal.library.characters.*;
import ideal.runtime.elements.*;
import ideal.runtime.logs.*;
import ideal.development.elements.*;
import ideal.machine.characters.unicode_handler;
import static ideal.development.names.operator_type.*;
import static ideal.development.names.punctuation.*;

public class operator extends debuggable implements action_name {
  public final operator_type the_operator_type;
  public final token_type name;
  public final simple_name alpha_name;
  public final precedence the_precedence;
  protected operator(final operator_type the_operator_type, final token_type name, final string alpha_name, final precedence the_precedence) {
    this.the_operator_type = the_operator_type;
    this.name = name;
    this.alpha_name = simple_name.make(alpha_name);
    this.the_precedence = the_precedence;
  }
  public simple_name symbol() {
    return this.alpha_name;
  }
  public string to_string() {
    return name_utilities.in_brackets(ideal.machine.elements.runtime_util.concatenate(ideal.machine.elements.runtime_util.concatenate(this.the_operator_type, new base_string(" ")), this.name));
  }
  public static final operator ASSIGN = new operator(operator_type.INFIX, punctuation.EQUALS, new base_string("assign"), precedence.ASSIGNMENT);
  public static final operator MULTIPLY = new operator(operator_type.INFIX, punctuation.ASTERISK, new base_string("multiply"), precedence.MULTIPLICATIVE);
  public static final operator DIVIDE = new operator(operator_type.INFIX, punctuation.SLASH, new base_string("divide"), precedence.MULTIPLICATIVE);
  public static final operator MODULO = new operator(operator_type.INFIX, punctuation.PERCENT, new base_string("modulo"), precedence.MULTIPLICATIVE);
  public static final operator ADD = new operator(operator_type.INFIX, punctuation.PLUS, new base_string("add"), precedence.ADDITIVE);
  public static final operator SUBTRACT = new operator(operator_type.INFIX, punctuation.MINUS, new base_string("subtract"), precedence.ADDITIVE);
  public static final operator NEGATE = new operator(operator_type.PREFIX, punctuation.MINUS, new base_string("negate"), precedence.ADDITIVE);
  public static final operator PRE_INCREMENT = new operator(operator_type.PREFIX, punctuation.PLUS_PLUS, new base_string("pre_increment"), precedence.UNARY);
  public static final operator CONCATENATE = new operator(operator_type.INFIX, punctuation.PLUS_PLUS, new base_string("concatenate"), precedence.CONCATENATE);
  public static final operator EQUAL_TO = new operator(operator_type.INFIX, punctuation.EQUALS_EQUALS, new base_string("equal_to"), precedence.EQUALITY);
  public static final operator NOT_EQUAL_TO = new operator(operator_type.INFIX, punctuation.EXCLAMATION_MARK_EQUALS, new base_string("not_equal_to"), precedence.EQUALITY);
  public static final operator LESS = new operator(operator_type.INFIX, punctuation.LESS_THAN, new base_string("less"), precedence.RELATIONAL);
  public static final operator GREATER = new operator(operator_type.INFIX, punctuation.GREATER_THAN, new base_string("greater"), precedence.RELATIONAL);
  public static final operator LESS_EQUAL = new operator(operator_type.INFIX, punctuation.LESS_THAN_EQUALS, new base_string("less_equal"), precedence.RELATIONAL);
  public static final operator GREATER_EQUAL = new operator(operator_type.INFIX, punctuation.GREATER_THAN_EQUALS, new base_string("greater_equal"), precedence.RELATIONAL);
  public static final operator COMPARE = new operator(operator_type.INFIX, punctuation.LESS_THAN_EQUALS_GREATER_THAN, new base_string("compare"), precedence.RELATIONAL);
  public static final operator BITWISE_AND = new operator(operator_type.INFIX, punctuation.AMPERSAND, new base_string("bitwise_and"), precedence.BITWISE_AND);
  public static final operator BITWISE_XOR = new operator(operator_type.INFIX, punctuation.CARET, new base_string("bitwise_xor"), precedence.BITWISE_XOR);
  public static final operator BITWISE_OR = new operator(operator_type.INFIX, punctuation.VERTICAL_BAR, new base_string("bitwise_or"), precedence.BITWISE_OR);
  public static final operator LOGICAL_AND = new operator(operator_type.INFIX, punctuation.AMPERSAND_AMPERSAND, new base_string("logical_and"), precedence.LOGICAL_AND);
  public static final operator LOGICAL_OR = new operator(operator_type.INFIX, punctuation.VERTICAL_BAR_VERTICAL_BAR, new base_string("logical_or"), precedence.LOGICAL_OR);
  public static final operator LOGICAL_NOT = new operator(operator_type.PREFIX, punctuation.EXCLAMATION_MARK, new base_string("logical_not"), precedence.UNARY);
  public static final operator GENERAL_OR = new operator(operator_type.INFIX, keywords.OR, new base_string("general_or"), precedence.LOGICAL_OR);
  public static final operator ADD_ASSIGN = new operator(operator_type.INFIX, punctuation.PLUS_EQUALS, new base_string("add_assign"), precedence.ASSIGNMENT);
  public static final operator SUBTRACT_ASSIGN = new operator(operator_type.INFIX, punctuation.MINUS_EQUALS, new base_string("subtract_assign"), precedence.ASSIGNMENT);
  public static final operator MULTIPLY_ASSIGN = new operator(operator_type.INFIX, punctuation.ASTERISK_EQUALS, new base_string("multiply_assign"), precedence.ASSIGNMENT);
  public static final operator CONCATENATE_ASSIGN = new operator(operator_type.INFIX, punctuation.PLUS_PLUS_EQUALS, new base_string("concatenate_assign"), precedence.ASSIGNMENT);
  public static final cast_type SOFT_CAST = new cast_type(punctuation.DOT_GREATER_THAN, new base_string("soft_cast"));
  public static final cast_type HARD_CAST = new cast_type(punctuation.EXCLAMATION_GREATER_THAN, new base_string("hard_cast"));
  public static final operator IS_OPERATOR = new operator(operator_type.INFIX, keywords.IS, new base_string("is_operator"), precedence.RELATIONAL);
  public static final operator IS_NOT_OPERATOR = new operator(operator_type.INFIX, keywords.IS_NOT, new base_string("is_not_operator"), precedence.RELATIONAL);
  public static final operator ALLOCATE = new operator(operator_type.PREFIX, keywords.NEW, new base_string("allocate"), precedence.UNARY);
}
