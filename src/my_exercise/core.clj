(ns my-exercise.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]] ;Default configs of ring middleware
            [ring.middleware.reload :refer [wrap-reload]] ;Middleware that reloads modified namespaces on each request.
            [my-exercise.home :as home]))                   ;homepage (home.clj)

(defroutes app
  (GET "/" [] home/page)
  (POST "/search" [& content]
    (str "<h1>Request: " (type content) (get content %1) content"</h1>"))
  (route/resources "/")
  (route/not-found "Not found"))

(def handler
  (-> app
      (wrap-defaults site-defaults)
      wrap-reload))
