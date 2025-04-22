DO $$
    BEGIN
        IF EXISTS (
            SELECT 1
            FROM information_schema.tables
            WHERE table_name = 'receiver'
        ) THEN
            EXECUTE 'ALTER TABLE receiver DROP CONSTRAINT IF EXISTS uk2o1lmleg9c6ephnle40pt7rn3';
        END IF;
    END
$$;
