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

(defproject sections-to-chapter "1.0.0"
  :description "A tool for Re:VIEW. Merge section files into one chapter file."
  :url "https://github.com/7pairs/sections-to-chapter"
  :license {:name "Apache License, Version 2.0"
            :url "https://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.clojure/clojure "1.10.0"]]
  :main sections-to-chapter.core
  :repl-options {:init-ns sections-to-chapter.core})
