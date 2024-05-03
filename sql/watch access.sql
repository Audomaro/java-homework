SELECT 
    grantee AS usuario,
    table_catalog AS base_de_datos,
    string_agg(privilege_type, ', ') AS privilegios
FROM 
    information_schema.role_table_grants
WHERE 
    grantee = 'larku'
GROUP BY 
    grantee, table_catalog;