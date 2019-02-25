TYPE=VIEW
query=select `sr1`.`Stop` AS `StartStop`,`sr2`.`Stop` AS `EndStop`,`sr1`.`Route` AS `Route`,(`sr2`.`Position` - `sr1`.`Position`) AS `StopCount` from `linan`.`stop_route` `sr1` join `linan`.`stop_route` `sr2` where ((`sr1`.`Route` = `sr2`.`Route`) and (`sr1`.`Position` < `sr2`.`Position`))
md5=f7935c277b0f158699c2c5e498d4a95c
updatable=1
algorithm=0
definer_user=root
definer_host=localhost
suid=2
with_check_option=0
timestamp=2018-12-21 08:23:44
create-version=1
source=select\n        sr1.Stop as StartStop,   \n        sr2.Stop as EndStop,   \n        sr1.Route as Route,   \n        sr2.Position-sr1.Position as StopCount   \n    from\n        stop_route sr1,\n        stop_route sr2\n    where\n        sr1.Route=sr2.Route\n        and sr1.Position<sr2.Position
client_cs_name=utf8
connection_cl_name=utf8_general_ci
view_body_utf8=select `sr1`.`Stop` AS `StartStop`,`sr2`.`Stop` AS `EndStop`,`sr1`.`Route` AS `Route`,(`sr2`.`Position` - `sr1`.`Position`) AS `StopCount` from `linan`.`stop_route` `sr1` join `linan`.`stop_route` `sr2` where ((`sr1`.`Route` = `sr2`.`Route`) and (`sr1`.`Position` < `sr2`.`Position`))
