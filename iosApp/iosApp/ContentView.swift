import SwiftUI
import shared

struct ContentView: View {
    let greeting = Greeting()
    
    @State var greet = "Loading..."
    @State var username: String = ""
    @State var password: String = ""
    
    func load(){
        greeting.greeting{ result, error in
            if let result = result {
                self.greet = result
            } else if let error = error {
                greet = "Error: \(error)"
            }
        }
    }
    
    func login() {
        let utf8str = password.data(using: .utf8)
        
        if let base64Encoded = utf8str?.base64EncodedString(options: Data.Base64EncodingOptions(rawValue: 0)) {
            print("Encoded: \(base64Encoded)")
            let str = "\(username):\(base64Encoded)"
            let user = str.data(using: .utf8)
            
            if let base64Encoded2 = user?.base64EncodedString(options: Data.Base64EncodingOptions(rawValue: 0)) {
                print("Encoded2: \(base64Encoded2)")
                greeting.login(authorization: base64Encoded2) { result, error in
                    if let result = result {
                        self.greet = result
                    } else if let error = error {
                        greet = "Error: \(error)"
                    }
                }
            }
            
            
            
        }
    }
    
    var body: some View {
                 Text(greet).onAppear(){
                     load()
                 }
        NavigationView {
            Form {
                TextField("Username", text: $username).autocapitalization(/*@START_MENU_TOKEN@*/.none/*@END_MENU_TOKEN@*/)
                TextField("Password", text: $password).autocapitalization(/*@START_MENU_TOKEN@*/.none/*@END_MENU_TOKEN@*/)
                Section{
                    Button(action: {
                        login()
                    }) {
                        Text("Login")
                    }
                }
            }
            .navigationBarTitle("Login")
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
