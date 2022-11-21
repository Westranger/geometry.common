package de.westranger.geometry.common.simple;

public final class ArcCircle extends Circle {

    private final Point2D arcStart;
    private final Point2D arcEnd;
    private final double arcStartAngle;
    private final double arcLength;

    // TODO warum braucht man den arcStartAngle ? es sind doch schon beide Punkte: start und ende gegeben
    // TODO ggf überprüfung im Konstruktor ob arc length mit den Start und Endpunkten übereinstimmt
    public ArcCircle(final Point2D center, final Point2D arcStart, final Point2D arcEnd, final double arcStartAngle, final double arcLength, final double radius) {
        super(center, radius);
        if (Double.isNaN(arcLength)) {
            throw new IllegalArgumentException("arc length must not be NaN");
        }
        if (Double.isInfinite(arcLength)) {
            throw new IllegalArgumentException("arc length must not be Inf");
        }

        this.arcStart = arcStart;
        this.arcEnd = arcEnd;
        this.arcStartAngle = arcStartAngle;
        this.arcLength = arcLength;
    }

    public Point2D getArcStart() {
        return arcStart;
    }

    public Point2D getArcEnd() {
        return arcEnd;
    }

    public double getArcStartAngle() {
        return arcStartAngle;
    }

    public double getArcLength() {
        return arcLength;
    }

    @Override
    public BoundingBox getBoundingBox() {
        // TODO NYI
        return null;
    }

    /*

    bool isWithin(const double start, const double end, const double value)
{
  const double start_clamped =
      Eigen::Rotation2Dd(start).smallestPositiveAngle();
  const double end_clamped = Eigen::Rotation2Dd(end).smallestPositiveAngle();
  const double value_clamped =
      Eigen::Rotation2Dd(value).smallestPositiveAngle();

  if (start_clamped < end_clamped)
  {
    return start_clamped <= value_clamped && value_clamped <= end_clamped;
  }
  return start_clamped <= value_clamped || value_clamped <= end_clamped;
}


std::pair<Eigen::Vector2d, Eigen::Vector2d> SVGFormat::Arc::getMinMax()
{
  const double x1 = std::cos(start) * radius;
  const double x2 = std::cos(start + arc_len) * radius;
  const double y1 = std::sin(start) * radius;
  const double y2 = std::sin(start + arc_len) * radius;

  double x_min = std::min(x1, x2);
  double x_max = std::max(x1, x2);
  double y_min = std::min(y1, y2);
  double y_max = std::max(y1, y2);

  const double tmp_start = arc_len < 0.0 ? start + arc_len : start;
  const double tmp_end = arc_len < 0.0 ? start : start + arc_len;

  if (isWithin(tmp_start, tmp_end, 0.0))
  {
    x_max = radius;
  }

  if (isWithin(tmp_start, tmp_end, M_PI_2))
  {
    y_max = radius;
  }

  if (isWithin(tmp_start, tmp_end, M_PI))
  {
    x_min = -radius;
  }

  if (isWithin(tmp_start, tmp_end, M_PI + M_PI_2))
  {
    y_min = -radius;
  }

  return std::make_pair(Eigen::Vector2d(center_x + x_min, center_y + y_min),
                        Eigen::Vector2d(center_x + x_max, center_y + y_max));
}

void SVGFormat::Arc::toSVG(std::ofstream& stream)
{
  stream << "\t";
  stream << "<path d=\"M ";
  stream << -(center_y + std::sin(start) * radius) * 1000;
  stream << ' ';
  stream << -(center_x + std::cos(start) * radius) * 1000;
  stream << " A ";
  stream << radius * 1000;
  stream << ' ';
  stream << radius * 1000;
  stream << ' ';
  stream << start * 180.0 / M_PI;
  stream << ' ';
  stream << (arc_len > M_PI || arc_len < -M_PI ? '1' : '0');
  stream << ' ';
  stream << (arc_len > 0.0 ? '0' : '1');
  stream << ' ';

  const auto end_pos =
      Eigen::Vector2d(center_x, center_y) +
      Eigen::Rotation2D(start + arc_len) * Eigen::Vector2d(radius, 0.0);

  stream << -end_pos.y() * 1000;
  stream << ' ';
  stream << -end_pos.x() * 1000;
  stream << "\" stroke=\"";
  stream << stroke_color;
  stream << "\" stroke-width=\"";
  stream << stroke_width * 1000;
  stream << "\" fill=\"";
  stream << fill_color;
  stream << "\" />\n";
}
     */
}
