# Grand Unified Software Quality Tool: Documentation generation

The general idea here is to run each client tool to generate documentation, then read that documentation (if in HTML, via `clj-tagsoup`), integrate it, and emit the integrated documentation as HTML (via `enlive`).

## Broad structure of the final HTML

### Annotated source files

For each source (.clj, .cljc, .cljs) file in the project, a single HTML formatted 'annotated source' file will be output showing issues detected by all of the various tools.

Most of the tools we're working with are file oriented and line oriented, rather than structure oriented. While I am utterly convinced this is a wrong choice for any Lisp tool, converting it all to structure oriented is a job outwith the scope of this exercise, so we'll go with two essential levels of structure, as follows

```html
    <div class="top-level-form">
        <div class="line warn" id="line-147">
            <code class="src">147                       (and (number? q) (> q 0)))</code>
            <dl>
              <dt class="cloverage"><a href="">Cloverage</a></dt>
              <dd class="cloverage info">9 out of 10 forms covered</dd>
              <dt class="kibit"><a href="https://github.com/jonase/kibit">Kibit</a></dt>
              <dd class="kibit warn">Consider using: <code>(pos? q)</code> instead of: <code>(> q 0)</code></dd>
            </dl>
        </div>
    </div>
```

That is to say,

1. for each top level form we'll have a div with the class `top-level-form`, and, ideally, an id that reflects its content where possible;
2. for each line we'll have a div within the div for the relevant top level form, having the class `line` and the id `line-xxx`, where `xxx` is the serial number of the line within the file.

The line div may also have a class indicating the **seriousness** of any issues found by any tool within the line, taken from:

* `info`
* `warn`
* `error`
* `fail`

It's possible that another level of seriousness is needed between `info` and `error` - not all warnings are as strong as one another. Where two or more tools report an issue, the greatest seriousness will be used in the class of the line div.

Within the line div, we'll have

1. a `code` element containing the source line copied from the source file, with the line number prepended;
2. a `dl` element containing one `dt`/`dd` pair for each tool reporting an issue with that line, where:
3. the `dt` element shall have a class derived from the name of the tool, and shall contain as text the name of the tool, and probably a link to its documentation;
4. the `dd` element shall have the same class derived from the name of the tool, and an additional class derived from the **seriousness** list above
