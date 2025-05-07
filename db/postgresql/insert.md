

## insert or update
> https://stackoverflow.com/a/76681234/6182927  
```sql
INSERT INTO tablename (id, username, password, level, email, update_count) 

-- if id doesn't exist, do insert
VALUES (1, 'John', 'qwerty', 5, 'john@mail.com', 0) 

-- how to check for duplicates (more versatile: could use any unique index here)
ON CONFLICT (id) 
DO UPDATE 
SET 
  -- update duplicate clause
  username=EXCLUDED.username, -- references proposed insertion row
  password=EXCLUDED.password,
  level=EXCLUDED.level,
  email=EXCLUDED.email,
  update_count=tablename.update_count+1 -- reference existing row
```
