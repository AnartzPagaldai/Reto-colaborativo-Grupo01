CREATE OR REPLACE PACKAGE BODY calendario03 AS
    procedure emparejarEmpleados03 
    as
        numEmpleados03 number(3);
        fechaRepedita03 number(1);
    begin
        select count(*) into numEmpleados03 from empleados_equipos03;
        if mod(numEmpleados03, 2) = 0 then
            for i in (select emp_no03 from empleados_equipos03) loop
                for e in (select emp_no03 from empleados_equipos03) loop
                
                    SELECT COUNT(*) INTO fechaRepedita03 FROM calendario_parejas03
                    WHERE (id_empleado103 = i.emp_no03 AND id_empleado203 = e.emp_no03 AND fecha_jornada03 = SYSDATE)
                    OR (id_empleado103 = e.emp_no03 AND id_empleado203 = i.emp_no03 AND fecha_jornada03 = SYSDATE);
                
                    if i.emp_no03 != e.emp_no03 and fechaRepedita03 = 0 then
                        insert into calendario_parejas03(id_empleado103, id_empleado203, fecha_jornada03)
                            values(i.emp_no03, e.emp_no03, SYSDATE);
                    end if;
                end loop;
            end loop;
        else
            dbms_output.put_line('numero total de empleados: ' || numEmpleados03);
        end if;
    end emparejarEmpleados03;
    
    procedure listarParejas03(c_listaParejas03 OUT tcursor03) 
    as
    begin
      open c_listaParejas03 for 
        select c.id_empleado103, e.apellido03 as apellido031, c.id_empleado203, 
            (select apellido03 from empleados_equipos03 where c.id_empleado203 = emp_no03) as apellido032,
            c.fecha_jornada03
        from calendario_parejas03 c, empleados_equipos03 e 
        where c.id_empleado103 = e.emp_no03 order by c.id_empleado103;
    end listarParejas03;
    
    procedure listarMisParejas03(apellido03_buscar IN varchar2, c_listaMisParejas03 OUT tcursor03) 
    as
    begin 
        open c_listaMisParejas03 for 
        select c.id_empleado103, e.apellido03 as apellido031,
        (select apellido03 from empleados_equipos03 where c.id_empleado203 = emp_no03) as apellido032, 
        c.fecha_jornada03
        from calendario_parejas03 c, empleados_equipos03 e 
        where c.id_empleado103 = e.emp_no03 and lower(e.apellido03) = lower(apellido03_buscar)
        union
        select c.id_empleado103, 
        (select apellido03 from empleados_equipos03 where c.id_empleado103 = emp_no03) as apellido031,
        e.apellido03 as apellido031,
        c.fecha_jornada03
        from calendario_parejas03 c, empleados_equipos03 e 
        where c.id_empleado203 = e.emp_no03 and lower(e.apellido03) = lower(apellido03_buscar);
        
    end listarMisParejas03;
end calendario03;
