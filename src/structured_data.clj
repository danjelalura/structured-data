
(ns structured-data)


(defn do-a-thing [x]
  (let [xx (+ x x)]

    (Math/pow xx xx))
  )
                                        ;Write the function (spiff v) that takes a vector and returns the sum of 
                                        ;the first and third elements of the vector. What happens when you pass in a vector that is too short?


(def spiff [v]
  (+ (get v 0) (get v 2))
  )



                                        ;Write the function (cutify v) that takes a vector as a parameter and adds "<3" to its end.

(defn cutify [v]
  (conj v "<3")  

  )


(defn spiff-destructuring [[v1 v2 v3 & vn]]
  (+ v1 v3)
  )

(defn point [x y]
  [x y])

(defn rectangle [bottom-left top-right]
  [bottom-left top-right])


(defn height [rectangle]
  (let [[[x1 y1] [x2 y2]] rectangle]
    (- y2 y1)
    )

  )

(defn width [rectangle]
  (let [[[x1 y1] [x2 y2]] rectangle]
    (- x2 x1)
    )

  )

(defn square? [rectangle]
  (= (height rectangle) (width rectangle)) 
  )

(defn area [rectangle]
  (* (height rectangle) (width rectangle)) 

  )

(defn contains-point? [rectangle point]
  (let [[[x1 y1] [x2 y2]] rectangle  [x3 y3] point]
    (and (<= x1 x3 x2) (<= y1 y3 y2))

    )
  )

                                        ; (and (<= x1 x3 x2) (<= y2 y3 y1)


(defn contains-rectangle? [outer inner]
  (let [[[x1 y1] [x2 y2]] outer [[x3 y3] [x4 y4]] inner]   
    (and  (contains-point? outer [x3 y3])  
          (contains-point? outer [x4 y4])))
  )

(defn title-length [book]
  (count (:title book))
  )

(defn author-count [book]
  (count (:authors book)) 

  )

(defn multiple-authors? [book]
  (if (= (author-count book) 1) false true)  

  )

                                        ;Use assoc and conj to write the function (add-author book new-author)
                                        ; that takes a book and an author as a parameter and adds author to books authors.


(defn add-author [book new-author] 

                                        ; initial solution
                                        ;(let [list-author (book :authors) updated-list (conj list-author new-author)] (assoc book :authors updated-list)) 

                                        ;the more elaborate one
  (assoc book :authors (conj (book :authors) new-author))
  )


(defn alive? [author]
  (if (= true (contains?  author :death-year) ) false true) 

  )

(defn element-lengths [collection]
  (map count collection) 
  )


(defn mungefy [collection]
  (let [munge (fn[x] (+ x 42))] 
    (map munge collection))
  )



(defn second-elements [collection]
  (let [second-element (fn [x] (get x 1))]  (map second-element collection))

  )

(defn titles [books]
  
  (let [title (fn [x] (:title x))] (map title books))

  )

(defn author-names [books]

  (let [author-name (fn[x] (map :name (:authors x)))] (apply concat (map author-name books)) )

  )


(defn monotonic? [a-seq]
  (or (apply <= a-seq)(apply >= a-seq))
  )

(defn stars [n]
  :-)

(defn toggle [a-set elem]
  (cond (= true (contains? a-set elem)) (disj a-set elem)
        :else (conj a-set elem)
        )
  
  )

(defn contains-duplicates? [a-seq]
  (if (= (count (set a-seq)) (count a-seq)) false  true)
  )

(defn old-book->new-book [book]
  (assoc book :authors (set (book :authors)))   
  )


(defn has-author? [book author]
  (contains? (set (:authors book)) author))

                                        ;Write the function (authors books) that returns the authors of every book in books as a set.
(defn authors [books]
                                        ;(let [author (fn [book]  (map :name (:authors book)) )] (clojure.set/union (set (map author books))))
  (clojure.set/union (set (map :authors books)))
  )

(defn all-author-names [books]
  (set (map :name (authors books)))   
  )

(defn author->string [author]
  (let [author-name (:name author) 
        birth (:birth-year author) 
        death (:death-year author)]
    (if (or birth death) (str author-name "(" birth "-" death ")") (str author-name) ) 
    ) )

(defn authors->string [authors]
  (apply str (interpose ", "(map author->string authors)))
  )


(defn book->string [book]
  (let [title (:title book) authors (authors->string (:authors book))] 
    (str title ", written by " authors "."))
  )

                                        ;(books->string [cities])
                                        ;=> "1 book. The City and the City, written by China Miéville (1972 - )."

(defn books->string [books]
  (let [count-books (count books) the-rest (fn [bookss] (apply str (map book->string bookss)))] 
    (cond (== count-books 0) (apply str "No books.")
          (== count-books 1) (apply str count-books " book,  " (the-rest books))
          :else
          (apply str count-books " books, " (the-rest books)))

    )  

  )



                                        ;Write the function (books-by-author author books).
                                        ;Hint: has-author?
                                        ;

(defn books-by-author [author books]

  (filter (fn [book] (has-author? book author)) books)

  )

(defn author-by-name [name authors]
  (filter (fn [author name] (contains? name author)) authors)    

  )

(defn living-authors [authors]
  (filter alive?  authors)  
  )

(defn has-a-living-author? [book]
  (not (empty? (living-authors (:authors book))))

  ) 


(defn books-by-living-authors [books]
  (filter has-a-living-author? books) 
  )

                                        ; %________%
