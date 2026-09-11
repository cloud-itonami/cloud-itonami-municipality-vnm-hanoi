(ns ordinance.facts
  "Municipal-ordinance compliance catalog for Hanoi -- the
  THIRTY-THIRD municipality-level entry (see cloud-itonami-municipality-jpn-tokyo,
  -usa-washington-dc, -gbr-london, -can-toronto, -deu-berlin, -fra-paris,
  -nld-amsterdam, -esp-madrid, -kor-seoul, -ita-roma, -aus-sydney,
  -arg-buenos-aires, -fin-helsinki, -dnk-copenhagen, -nor-oslo,
  -bel-brussels, -chl-santiago, -col-bogota, -cri-san-jose,
  -bra-sao-paulo, -ury-montevideo, -zaf-cape-town, -ecu-quito,
  -swe-gothenburg, -pry-asuncion, -mex-guadalajara, -fra-lyon,
  -ind-new-delhi, -pol-warsaw, -ken-nairobi, -tha-bangkok, -are-abu-dhabi
  for the first thirty-two) per ADR-2607141700
  (cloud-itonami-compliance-fact-federation).

  Vietnam's capital moved historically between Hanoi (from 1010),
  Hue (Nguyen Dynasty, early 19th century), and back to Hanoi (25
  April 1976, post-reunification National Assembly decision) -- the
  pre-existing organization.edn's Hanoi/Q1858 designation matches this
  current, post-1976 status, so no P36 historical-capital ingestion
  bug applies here.

  Law on the Capital (Luật Thủ đô, Law No. 39/2024/QH15) -- title,
  law number, and passage date directly confirmed via
  english.luatvietnam.vn (a private legal-translation distributor of
  Vietnam's Official Gazette texts, NOT a government domain itself,
  but citing genuine official-gazette content -- similar tier to
  Mereller's UAE legal translations used one tick earlier). Decision
  No. 33/2023/QĐ-UBND (Administrative procedures restructuring in
  culture and sports) -- directly confirmed via
  english.hanoi.gov.vn's own homepage listing.

  An ordinance not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url/date.")

(def catalog
  "municipality-slug -> vector of ordinance entries."
  {"hanoi"
   [{:ordinance/id "hanoi.law-on-the-capital-39-2024-qh15"
     :ordinance/title "Law on the Capital (Luật Thủ đô)"
     :ordinance/municipality "hanoi"
     :ordinance/country "VNM"
     :ordinance/kind :local-act
     :ordinance/number "No. 39/2024/QH15"
     :ordinance/url "https://english.luatvietnam.vn/hanh-chinh/law-on-the-capital-2024-360812-d1.html"
     :ordinance/url-provenance :luatvietnam-legal-translation-mirror
     :ordinance/enacted-date "2024-06-28"
     :ordinance/retrieved-at "2026-07-16"
     :ordinance/topic #{:governance}}
    {:ordinance/id "hanoi.decision-33-2023-qd-ubnd"
     :ordinance/title "Decision 33/2023/QĐ-UBND concerning administrative procedures restructuring in culture and sports"
     :ordinance/municipality "hanoi"
     :ordinance/country "VNM"
     :ordinance/kind :ordinance
     :ordinance/number "33/2023/QĐ-UBND"
     :ordinance/url "https://english.hanoi.gov.vn/"
     :ordinance/url-provenance :official-hanoi-gov-vn
     :ordinance/enacted-date "2023-12-20"
     :ordinance/retrieved-at "2026-07-16"
     :ordinance/topic #{:governance}}]})

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
      :note (str "cloud-itonami-municipality-vnm-hanoi Wave 0 (ADR-2607141700): "
                 (count (get catalog "hanoi")) " Hanoi entries seeded "
                 "with luatvietnam.vn/hanoi.gov.vn citations. "
                 "Extend `ordinance.facts/catalog`, never fabricate an id/url.")})))

(defn by-topic [muni topic]
  (filterv #(contains? (:ordinance/topic %) topic) (spec-basis muni)))
