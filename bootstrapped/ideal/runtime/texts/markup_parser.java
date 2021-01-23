// Autogenerated from runtime/texts/markup_parser.i

package ideal.runtime.texts;

import ideal.library.elements.*;
import ideal.library.characters.*;
import ideal.library.texts.*;
import ideal.runtime.elements.*;
import ideal.library.channels.output;

public class markup_parser {
  public final markup_grammar grammar;
  public final procedure1<Void, string> error_reporter;
  public markup_parser(final markup_grammar grammar, final procedure1<Void, string> error_reporter) {
    this.grammar = grammar;
    this.error_reporter = error_reporter;
  }
  public void report_error(final string error_message) {
    this.error_reporter.call(error_message);
  }
  public text_element parse(final string text) {
    return this.grammar.parse(text, this);
  }
}
