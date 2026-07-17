(ns culture.facts
  "Regional-culture catalog for Hanoi -- local dishes, protected products,
  beverages, festivals and heritage sites, piggybacked onto this
  municipality compliance repo per ADR-2607171400
  (cloud-itonami-municipality-culture-catalog, in com-junkawasaki/root),
  sibling namespace to `ordinance.facts` (ADR-2607141700).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "municipality-slug -> vector of culture entries."
  {"hanoi"
   [{:culture/id "hanoi.dish.pho"
     :culture/name "Pho"
     :culture/name-local "phở"
     :culture/municipality "hanoi"
     :culture/country "VNM"
     :culture/kind :dish
     :culture/summary "Vietnamese noodle soup that originated in Northern Vietnam in the early 20th century, its modern form emerging in villages southeast of Hanoi in Nam Dinh Province."
     :culture/url "https://en.wikipedia.org/wiki/Pho"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "hanoi.dish.bun-cha"
     :culture/name "Bun cha"
     :culture/name-local "bún chả"
     :culture/municipality "hanoi"
     :culture/country "VNM"
     :culture/kind :dish
     :culture/summary "Vietnamese dish of grilled pork and rice noodles with herbs and dipping sauce, thought to have originated from Hanoi."
     :culture/url "https://en.wikipedia.org/wiki/B%C3%BAn_ch%E1%BA%A3"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "hanoi.dish.cha-ca-la-vong"
     :culture/name "Cha ca La Vong"
     :culture/name-local "chả cá Lã Vọng"
     :culture/municipality "hanoi"
     :culture/country "VNM"
     :culture/kind :dish
     :culture/summary "Vietnamese grilled fish dish originally from Hanoi, made with catfish and turmeric and served with rice noodles and shrimp-based sauce."
     :culture/url "https://en.wikipedia.org/wiki/Ch%E1%BA%A3_c%C3%A1_L%C3%A3_V%E1%BB%8Dng"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "hanoi.product.com"
     :culture/name "Com (green rice)"
     :culture/name-local "cốm"
     :culture/municipality "hanoi"
     :culture/country "VNM"
     :culture/kind :product
     :culture/summary "Flattened, chewy green rice made from young rice kernels, traditionally produced at the Com Vong village in Hanoi."
     :culture/url "https://en.wikipedia.org/wiki/C%E1%BB%91m"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "hanoi.beverage.egg-coffee"
     :culture/name "Egg coffee"
     :culture/name-local "cà phê trứng"
     :culture/municipality "hanoi"
     :culture/country "VNM"
     :culture/kind :beverage
     :culture/summary "Vietnamese coffee drink of egg yolks, sugar, condensed milk and robusta coffee, originating from Hanoi where Giang Cafe is credited with developing the recipe in the late 1940s."
     :culture/url "https://en.wikipedia.org/wiki/Egg_coffee"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "hanoi.craft.bat-trang-ceramics"
     :culture/name "Bat Trang ceramics"
     :culture/name-local "Bát Tràng"
     :culture/municipality "hanoi"
     :culture/country "VNM"
     :culture/kind :craft
     :culture/summary "Pottery and porcelain from Bat Trang, the 'Village of Pottery and Porcelain' in Hanoi's Red River Delta area, with ceramic production documented since at least the 14th century."
     :culture/url "https://en.wikipedia.org/wiki/B%C3%A1t_Tr%C3%A0ng"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "hanoi.festival.giong-festival"
     :culture/name "Giong Festival"
     :culture/name-local "Hội Gióng"
     :culture/municipality "hanoi"
     :culture/country "VNM"
     :culture/kind :festival
     :culture/summary "Traditional festival celebrated annually in many parts of Hanoi to honour the mythical hero Thanh Giong, listed as UNESCO Intangible Cultural Heritage."
     :culture/url "https://en.wikipedia.org/wiki/Gi%C3%B3ng_Festival"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "hanoi.heritage.imperial-citadel-thang-long"
     :culture/name "Imperial Citadel of Thang Long"
     :culture/name-local "Hoàng thành Thăng Long"
     :culture/municipality "hanoi"
     :culture/country "VNM"
     :culture/kind :heritage
     :culture/summary "Citadel complex in Hanoi spanning over a millennium of Vietnamese dynasties; its Central Sector was listed as a UNESCO World Heritage Site in 2010."
     :culture/url "https://en.wikipedia.org/wiki/Imperial_Citadel_of_Th%C4%83ng_Long"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "hanoi.heritage.temple-of-literature"
     :culture/name "Temple of Literature"
     :culture/name-local "Văn Miếu"
     :culture/municipality "hanoi"
     :culture/country "VNM"
     :culture/kind :heritage
     :culture/summary "Temple in Hanoi dedicated to Confucius, founded and first built in 1070 under Emperor Ly Thanh Tong."
     :culture/url "https://en.wikipedia.org/wiki/Temple_of_Literature,_Hanoi"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [muni] (get catalog muni))

(defn coverage
  ([] (coverage (keys catalog)))
  ([munis]
   (let [have (filter catalog munis)
         missing (remove catalog munis)]
     {:requested (count munis)
      :covered (count have)
      :covered-municipalities (vec (sort have))
      :missing-municipalities (vec (sort missing))
      :note (str "cloud-itonami-municipality-vnm-hanoi culture catalog "
                 "(ADR-2607171400): " (count (get catalog "hanoi"))
                 " Hanoi entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [muni kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis muni)))
