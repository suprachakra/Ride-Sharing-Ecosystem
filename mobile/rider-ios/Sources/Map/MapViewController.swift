import UIKit
import MapKit

class MapViewController: BaseViewController {
    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = "Map"
        let mapView = MKMapView(frame: self.view.bounds)
        mapView.autoresizingMask = [.flexibleWidth, .flexibleHeight]
        self.view.addSubview(mapView)
        // TODO: Integrate Google Maps SDK if needed
    }
}
