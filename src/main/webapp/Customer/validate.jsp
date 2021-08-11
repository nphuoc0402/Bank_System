<script>
    $(function() {
        // Initialize form validation on the registration form.
        // It has the name attribute "registration"
        $(".needs-validation").validate({
            // Specify validation rules
            rules: {
                // The key name on the left side is the name attribute
                // of an input field. Validation rules are defined
                // on the right side
                name: "required",
                phone: {
                    required: true,
                    // Specify that email should be validated
                    // by the built-in "email" rule
                    phone: true
                },
                email: {
                    required: true,
                    // Specify that email should be validated
                    // by the built-in "email" rule
                    email: true
                },
                password: {
                    required: true,
                    minlength: 5
                }
            },

            messages: {
                name: "Please enter your name",
                phone:{
                    required: "Please provide a phone",
                    minlength: "Your phone number  must be at least 10 or 11 number"
                },
                email: {
                    required: "Please provide a email",
                    minlength: "Your email must be at least 5 characters long"
                },
                email: "Please enter a valid email address"
            },
            // Make sure the form is submitted to the destination defined
            // in the "action" attribute of the form when valid
            submitHandler: function(form) {
                form.submit();
            }
        });
    });
</script>