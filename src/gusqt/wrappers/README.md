# Grand Unified Software Quality Tool: Wrappers

Wrappers are intended to produce unifiable output from the specific tools they wrap. This unifiable output as follows:

1. `as-edn` output in the form of a sequence of maps, with keys as follows:
# `column` (optional) the locus of the report within the line
# `file` the name of the source file analysed
# `line` the locus of the report
# `severity` one of `:fail :error :warn :info`
# `tool` (as a keyword) the name of the tool producing the report
# `text` Human readable (optionally markdown formatted) text of the report
2. `as-markdown` output in the form of human-readable markdown
