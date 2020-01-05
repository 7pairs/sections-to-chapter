; Copyright 2020 HASEBA Junya
;
; Licensed under the Apache License, Version 2.0 (the "License");
; you may not use this file except in compliance with the License.
; You may obtain a copy of the License at
;
;     http://www.apache.org/licenses/LICENSE-2.0
;
; Unless required by applicable law or agreed to in writing, software
; distributed under the License is distributed on an "AS IS" BASIS,
; WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
; See the License for the specific language governing permissions and
; limitations under the License.

(ns sections-to-chapter.core
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(defn review-file? [target]
  (string/starts-with? target "-"))

(defn get-file-name [target]
  (if-let [m (re-find #"^- *([^ ]+$)" target)]
    (m 1)))

(defn parse-catalog [path]
  (with-open [rdr (io/reader path)]
    (doall (for [line (line-seq rdr) :let [target (string/trim line)] :when (review-file? target)] (get-file-name target)))))

(defn -main [articles-path]
  (print (parse-catalog (str articles-path "/catalog.yml"))))
