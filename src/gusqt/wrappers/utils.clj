(ns gusqt.wrappers.utils
  "Utility functions useful to wrappers"
  (:require [clojure.string :as s]))


(defn reads-as-number?
  "if `x` is a number or is a string representation of a number,
  returns the numerical value of `x`; else false."
  [x]
  (let [v (try (read-string x) (catch Exception any))]
    (cond
      (number? x) x
      (number? v) v
      :else false)))

(reads-as-number? :foo)

(defn c-error-to-map
  ([text]
   (c-error-to-map text :unknown))
  ([text tool]
   (map
     #(let [lines (s/split % #"\n")
            [_ file line column severity message] (re-matches #"(.+):(\d+):(\d+): (\w+): (.*)" (first lines))]
        {:tool tool
         :file file
         :line line
         :column column
         :severity severity
         :message (s/trim (s/join "\n" (cons message (rest lines))))})
     (s/split text #"[\r\n|\n]+"))))


(c-error-to-map (slurp "../smeagol/kondo.txt") :kondo)


