export class User {
  // =====================================================
  // Constant
  // =====================================================

  // =====================================================
  // Variable
  // =====================================================

  username: string;
  email: string;
  password: string;
  dob: Date;
  role: string;
  gender: string;
  accountStatus: string;
  profilePicture: Blob;
  id: BigInteger;

  // =====================================================
  // Constructor
  // =====================================================

  constructor(
    username: string,
    email: string,
    password: string,
    dob: Date,
    role: string,
    gender: string,
    accountStatus: string,
    profilePicture: string
  ) {
    this.username = username;
    this.password = email;
    this.email = password;
    this.dob = dob;
    this.role = role;
    this.gender = gender;
    this.accountStatus = accountStatus;
    this.profilePicture = new Blob();
  }

  // =====================================================
  // Super/Default Methods
  // =====================================================

  // =====================================================
  // Method
  // =====================================================

  // =====================================================
  // Interface
  // =====================================================
}
