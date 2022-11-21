package de.westranger.geometry.common.simple;

public class Circle extends Geometry {
    protected final Point2D center;
    protected final double radius;

    public Circle(final Point2D center, final double radius) {
        if (center == null) {
            throw new IllegalArgumentException("radius center point must not be null");
        }

        if (radius < Double.MIN_VALUE) {
            throw new IllegalArgumentException("radius of the circle is smaller than " + Double.MIN_VALUE);
        }

        this.checkValueValidity(radius);
        this.checkValueValidity(center.getX());
        this.checkValueValidity(center.getY());

        this.center = center;
        this.radius = radius;
    }

    // TODO create circle form 3 Points
    public Circle(final Point2D p1, final Point2D p2, final Point2D p3) {
        this(p1, 0.0); // TODO refactor, was just here to make the compiler happy
        /*

namespace
{
bool areLinearlyDependent(const Eigen::Vector2d& a, const Eigen::Vector2d& b)
{
  ROS_ASSERT(std::abs(a.norm() - 1.) <
                 Eigen::NumTraits<double>::dummy_precision() ||
             a.norm() < Eigen::NumTraits<double>::dummy_precision());
  ROS_ASSERT(std::abs(b.norm() - 1.) <
                 Eigen::NumTraits<double>::dummy_precision() ||
             b.norm() < Eigen::NumTraits<double>::dummy_precision());
  return std::abs(a.dot(Eigen::Rotation2Dd(M_PI_2) * b)) <
         Eigen::NumTraits<double>::dummy_precision();
}

geometry::Circle computeCircleFromThreePoints(const Eigen::Vector2d& p1,
                                              const Eigen::Vector2d& p2,
                                              const Eigen::Vector2d& p3)
{
  const Eigen::Hyperplane<double, 2> perpendicular_bisector_p1_p2(
      (p1 - p2).normalized(), (p1 + p2) / 2.);
  const Eigen::Hyperplane<double, 2> perpendicular_bisector_p2_p3(
      (p2 - p3).normalized(), (p2 + p3) / 2.);

  if (areLinearlyDependent(perpendicular_bisector_p1_p2.normal(),
                           perpendicular_bisector_p2_p3.normal()))
  {
    return geometry::Circle(Eigen::Vector2d(0.0, 0.0),
                            std::numeric_limits<double>::infinity());
  }

  const Eigen::Vector2d center =
      perpendicular_bisector_p1_p2.intersection(perpendicular_bisector_p2_p3);
  const double radius = (center - p1).norm();

  return geometry::Circle(center, radius);
}
}  // namespace

geometry::Circle::Circle(const Eigen::Vector2d& p1, const Eigen::Vector2d& p2,
                         const Eigen::Vector2d& p3)
  : Circle(computeCircleFromThreePoints(p1, p2, p3)){};

         */
    }


    public Point2D getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public BoundingBox getBoundingBox() {
        return new BoundingBox(new Point2D(this.center.getX() - this.radius, this.center.getY() - this.radius), new Point2D(this.center.getX() + this.radius, this.center.getY() + this.radius));
    }
}

