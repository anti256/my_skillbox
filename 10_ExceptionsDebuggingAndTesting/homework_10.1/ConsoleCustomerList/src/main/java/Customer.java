public class Customer {
    private final String name;
    private final String phone;
    private final String email;

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Customer(String name, String phone, String email) {
        //проверка валидности email
        if ((!email.matches("([A-Za-z0-9]+).([A-Za-z0-9]+)[@]([A-Za-z]+).([A-Za-z]+)"))) {
         throw new IllegalArgumentException("Wrong e-mail format");
        }
        if (!phone.matches("\\+7[0-9]{10}")){//проверка валидного телефона
         throw new IllegalArgumentException("Wrong phone number format");
        }
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String toString() {
        return name + " - " + email + " - " + phone;
    }
}
