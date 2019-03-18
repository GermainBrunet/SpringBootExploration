drop view product_price_view;

CREATE VIEW product_price_view AS
SELECT 
  pr.id as pr_id,
  pps.id as pps_id,
  -- pp.id as pp_id,
  pr.name as productName,
  pr.model_number as productModelNumber,
  ws.url as url,
  pps.query_string as queryString,
  c.price as price,
  c.date as date
FROM
  product pr
INNER JOIN 
  product_price_source pps
ON
  pr.id = pps.product_id
INNER JOIN
  (SELECT pp.product_price_source_id as product_price_source_id, pp.price as price, pp.date as date from product_price pp
    WHERE 
  pp.date = (select max(ppb.date) from product_price ppb where ppb.product_price_source_id = pp.product_price_source_id)
   ) c
ON
  c.product_price_source_id = pps.id
INNER JOIN
  website ws
ON
  pps.website_id = ws.id
ORDER BY
  pr.id DESC,
  c.price ASC;
    
select * from product_price_view;



