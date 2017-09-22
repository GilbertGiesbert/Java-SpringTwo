

--- database

- db must exist before webapp starts
- id is bigserial and primary key
- varchar for name, pswd and email

@GeneratedValue(strategy=GenerationType.IDENTITY)
- strategy statement prevents 'hibernate_sequence' exception