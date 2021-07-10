// Autogenerated from runtime/formats/json_token.i

package ideal.runtime.formats;

import ideal.library.elements.*;
import ideal.library.characters.*;
import ideal.runtime.elements.*;

public enum json_token implements deeply_immutable_data, deeply_immutable_reference_equality, stringable {
  OPEN_BRACE('{'),
  CLOSE_BRACE('}'),
  OPEN_BRACKET('['),
  CLOSE_BRACKET(']'),
  COMMA(','),
  COLON(':');
  public final char token;
  private json_token(final char token) {
    this.token = token;
  }
  public string to_string() {
    return new base_string(toString());
  }
}
