# recidiffist-cli

A CLI for [recidiffist][recidiffist]. If you're not sure what this is you
probably want to look at that repo instead.

## Installation

recidiffist comes as a single binary. Put it on your `$PATH`.

You can build it yourself, if you have leiningen installed, GraalVM installed
(and `GRAALVM_HOME` configured) by calling `lein native-image`.

## Usage

recidiffist takes no patameters, takes stuff to diff on stdin, and produces the
diffs on stdout.

## Examples

Examples are given with shell heredocs for legibility. A value changes from 1 to 2:

```
./recidiffist <<EOF
{"a": 1}
{"a": 2}
EOF
{"added":[],"changed":[[["a"],1,2]],"removed":[]}
```

Old key disappears, new key appears:

```
./recidiffist <<EOF
heredoc> {"a": 1}
heredoc> {"b": 2}
heredoc> EOF
{"added":[[["b"],2]],"changed":[],"removed":[[["a"],1]]}
```

You can pass n objects and get n-1 diffs:

```
./recidiffist <<EOF
heredoc> {"a": 1}
heredoc> {"a": 2}
heredoc> {"a": 3}
heredoc> EOF
{"added":[],"changed":[[["a"],1,2]],"removed":[]}{"added":[],"changed":[[["a"],2,3]],"removed":[]}
```

Suggested pattern: subshell:

```sh
(aws s3 cp s3://my-bucket/state-from-yesterday - ; ./script-that-gets-state-right-now-and-writes-to-stdout.sh ) | recidiffist
```

Alternatively, command grouping:

```sh
{ aws s3 cp s3://my-bucket/state-from-yesterday - ; ./script-that-gets-state-right-now-and-writes-to-stdout.sh ; } | recidiffist
```

## License

Copyright © Latacora, LLC

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
