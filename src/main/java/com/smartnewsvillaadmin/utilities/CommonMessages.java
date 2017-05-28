package com.smartnewsvillaadmin.utilities;

/**
 *
 * @author mdabhi
 */
public enum CommonMessages {

    CODEEXPRIRED {
                @Override
                public String toString() {
                    return "Code is expired.Click here to resend code.";
                }
            },
    CODERESENDED {
                @Override
                public String toString() {
                    return "Code successful resend.Please check your email.";
                }
            },
    CODEREQUIRED {
                @Override
                public String toString() {
                    return "Please provide six digit code.";
                }
            },
    INVALIDCODE {
                @Override
                public String toString() {
                    return "Invalid code. try again.";
                }

            },
    PROFILEUPDATED {
                @Override
                public String toString() {
                    return "Profile successfily updated.";
                }

            },
    PASSWORDUPDATED {
                @Override
                public String toString() {
                    return "Password updated.";
                }

            }, PASSWORDNOTMATCH {
                @Override
                public String toString() {
                    return "Password does not match the confirm password.";
                }

            }, OLD_PASSWORDINCORRECT {
                @Override
                public String toString() {
                    return "Old password is incorrect.";
                }

            }
}
