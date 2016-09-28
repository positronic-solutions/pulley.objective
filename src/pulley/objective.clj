;; Copyright 2016 Positronic Solutions, LLC.
;;
;; This file is part of pulley.objective.
;;
;; pulley.objective is free software: you can redistribute it and/or modify
;; it under the terms of the GNU Lesser General Public License as published by
;; the Free Software Foundation, either version 3 of the License, or
;; (at your option) any later version.
;;
;; pulley.objective is distributed in the hope that it will be useful,
;; but WITHOUT ANY WARRANTY; without even the implied warranty of
;; MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
;; GNU General Public License for more details.
;;
;; You should have received a copy of the GNU Lesser General Public License
;; along with pulley.objective.  If not, see <http://www.gnu.org/licenses/>.
(ns pulley.objective)

(declare object-)

(deftype PersistentObject [attrs]
  clojure.lang.Associative
  (assoc [self k v]
    (object- (assoc attrs k v)))
  (cons [self entry]
    (let [[k v] entry]
      (assoc self k v)))
  (containsKey [self k]
    (contains? attrs k))
  (empty [self]
    (new PersistentObject (empty attrs)))
  (entryAt [self k]
    (. attrs (entryAt k)))
  (equiv [self other]
    (. attrs (equiv other)))
  (seq [self]
    (seq attrs))
  (valAt [self k]
    (get attrs k))
  (valAt [self k default]
    (get attrs k default))

  clojure.lang.Counted
  (count [self]
    (count attrs))

  clojure.lang.IFn
  (invoke [self k]
    (if (contains? self k)
      (get self k)
      (throw (new IllegalArgumentException (str "Attribute not found: " k)))))
  (invoke [self k arg]
    ((self k) self arg))
  (invoke [self k arg1 arg2]
    ((self k) self arg1 arg2))
  (invoke [self k arg1 arg2 arg3]
    ((self k) self arg1 arg2 arg3))
  (invoke [self k arg1 arg2 arg3 arg4]
    ((self k) self arg1 arg2 arg3 arg4))
  (invoke [self k arg1 arg2 arg3 arg4 arg5]
    ((self k) self arg1 arg2 arg3 arg4 arg5))
  (invoke [self k arg1 arg2 arg3 arg4 arg5 arg6]
    ((self k) self arg1 arg2 arg3 arg4 arg5 arg6))
  (invoke [self k arg1 arg2 arg3 arg4 arg5 arg6 arg7]
    ((self k) self arg1 arg2 arg3 arg4 arg5 arg6 arg7))
  (invoke [self k arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8]
    ((self k) self arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8))
  (invoke [self k arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9]
    ((self k) self arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9))
  (invoke [self k arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10]
    ((self k) self arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10))
  (invoke [self k arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11]
    ((self k) self arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11))
  (invoke [self k arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12]
    ((self k) self arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12))
  (invoke [self k arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12 arg13]
    ((self k) self arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12 arg13))
  (invoke [self k arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12 arg13 arg14]
    ((self k) self arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12 arg13 arg14))
  (invoke [self k arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12 arg13 arg14 arg15]
    ((self k) self arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12 arg13 arg14 arg15))
  (invoke [self k arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12 arg13 arg14 arg15 arg16]
    ((self k) self arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12 arg13 arg14 arg15 arg16))
  (invoke [self k arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12 arg13 arg14 arg15 arg16 arg17]
    ((self k) self arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12 arg13 arg14 arg15 arg16 arg17))
  (invoke [self k arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12 arg13 arg14 arg15 arg16 arg17 arg18]
    ((self k) self arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12 arg13 arg14 arg15 arg16 arg17 arg18))
  (invoke [self k arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12 arg13 arg14 arg15 arg16 arg17 arg18 arg19]
    ((self k) self arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12 arg13 arg14 arg15 arg16 arg17 arg18 arg19))
  (invoke [self k arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12 arg13 arg14 arg15 arg16 arg17 arg18 arg19 arg20]
    ((self k) self arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8 arg9 arg10 arg11 arg12 arg13 arg14 arg15 arg16 arg17 arg18 arg19 arg20))
  (applyTo [self args]
    (clojure.lang.AFn/applyToHelper self (cons self args))))

(defn- object- [attrs]
  (new PersistentObject attrs))

(defn map->object [attrs]
  (object- attrs))
