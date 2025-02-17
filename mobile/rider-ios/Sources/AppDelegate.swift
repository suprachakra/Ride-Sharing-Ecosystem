import UIKit

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    var window: UIWindow?

    func application(_ application: UIApplication,
                     didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        window = UIWindow(frame: UIScreen.main.bounds)
        // Set initial view controller; here using a storyboard or programmatically.
        window?.rootViewController = UINavigationController(rootViewController: BookingViewController())
        window?.makeKeyAndVisible()
        return true
    }
}
