// Autogenerated from development/constructs/constraint_construct.i

package ideal.development.constructs;

import ideal.library.elements.*;
import ideal.runtime.elements.*;
import ideal.development.elements.*;
import ideal.development.names.*;

public class constraint_construct extends base_construct {
  public final constraint_category the_constraint_category;
  public final construct expr;
  public constraint_construct(final constraint_category the_constraint_category, final construct expr, final origin generated_origin) {
    super(generated_origin);
    assert the_constraint_category != null;
    this.the_constraint_category = the_constraint_category;
    assert expr != null;
    this.expr = expr;
  }
  public @Override readonly_list<construct> children() {
    final base_list<construct> generated_result = new base_list<construct>();
    generated_result.append(this.expr);
    return generated_result;
  }
}
