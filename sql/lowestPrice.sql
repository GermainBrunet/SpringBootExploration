drop view product_lowest_price_view;

CREATE VIEW product_lowest_price_view AS
select
  p.id as id,
  p.name as product_name,
  p.model_number as model_number,
  p.year,
  m.name as manufacturer_name,
  c.lowestPrice as lowest_price
from 
  product p
inner join
  manufacturer m
on 
  p.manufacturer_id = m.id
inner join
  (select
    p.id as productId,
    min(pp.price) as lowestPrice
  from 
    product_price pp
  inner join
    product_price_source pps
  on
    pp.product_price_source_id = pps.id
  inner join
    product p
  on
    pps.product_id = p.id
  group by 
    p.id) c
on
  c.productId = p.id;