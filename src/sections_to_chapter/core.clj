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

(defn get-chapter-file-name [target]
  (if-let [m (re-find #"^- *([^ ]+$)" target)]
    (m 1)))

(defn parse-catalog [path]
  (with-open [r (io/reader path)]
    (doall (for [line (line-seq r) :let [target (string/trim line)] :when (review-file? target)] (get-chapter-file-name target)))))

(defn import-section? [target]
  (re-find #"^#@# *IMPORT-SECTION" target))

(defn get-section-file-name [target]
  (if-let [m (re-find #"^#@# *IMPORT-SECTION +([^ ]+)" target)]
    (m 1)))

(defn lower-level [target]
  (if (string/starts-with? target "=") (str "=" target) target))

(defn read-section-file [path]
  (with-open [r (io/reader path)]
    (string/join "\n" (for [line (line-seq r)] (lower-level line)))))

(defn marge-files! [input-dir output-dir chapter-file-name]
  (with-open [r (io/reader (str input-dir "/" chapter-file-name))]
    (with-open [w (io/writer (str output-dir "/" chapter-file-name))]
      (doseq [line (line-seq r)] (.write w (str (if (import-section? line) (read-section-file (str input-dir "/" (get-section-file-name line))) line) "\n"))))))

(defn -main [articles-dir]
  (doseq [chapter-file-name (parse-catalog (str articles-dir "/catalog.yml"))] (marge-files! (str articles-dir "/origin") articles-dir chapter-file-name)))
